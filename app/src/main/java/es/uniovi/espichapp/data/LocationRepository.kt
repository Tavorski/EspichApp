package es.uniovi.espichapp.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import es.uniovi.arqui.model.LocationDAO
import es.uniovi.espichapp.model.Location
import es.uniovi.espichapp.network.RestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class LocationRepository(private val locationDAO: LocationDAO,
                         private val dataStore: DataStore<Preferences>) {


    /**
     * CAMPOS
     */
    var isWifiConnected: Boolean = false

    /** 
     *  Funciones de acceso a la base de datos ROOM
     */
    fun getLocations() = locationDAO.getLocations()
    fun searchLocationsByName(query: String) = locationDAO.searchLocationsByName(query)
    fun getLocationByName(locationName: String) = locationDAO.getLocationByName(locationName)
    suspend fun deleteLocations() {
        CoroutineScope(Dispatchers.IO).launch {
            locationDAO.deleteLocations()
        }
        Log.d("DEBUG-Repo","DELETE locations_table")
    }
    suspend fun insertLocation(location: Location) {
        CoroutineScope(Dispatchers.IO).launch {
            locationDAO.insertLocation(location)
        }
        Log.d("DEBUG-Repo","INSERT '${location.Nombre}'")
    }

    /**
     *  Funciones de acceso al datastore
     */

    private object ParametersKeys {
        val USE_MOBILE_DATA = stringPreferencesKey("mobile_data")
        val LANGUAGE = stringPreferencesKey("language")
        val USE_DEFAULT_SYSTEM_THEME = booleanPreferencesKey("system_theme")
        val NIGHT_THEME = booleanPreferencesKey("night_theme")
    }
    private val defValues: Map<String,String> = mapOf(
        Pair("mobile_data","ANY"),
        Pair("language","ES"),
        Pair("system_theme","true"),
        Pair("theme","true"))

    val settingsParametersFlow: Flow<SettingsParameters> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e("DEBUG - LRepo", "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            mapSettingsParameters(preferences)
        }

    suspend fun fetchParameters() = mapSettingsParameters(dataStore.data.first().toPreferences())

    private fun mapSettingsParameters(prefs: Preferences): SettingsParameters {
        val useMobileData = prefs[ParametersKeys.USE_MOBILE_DATA] ?: UseMobileData.ANY.name
        val language = prefs[ParametersKeys.LANGUAGE] ?: Language.ES.name
        val useDefaultSystemTheme = prefs[ParametersKeys.USE_DEFAULT_SYSTEM_THEME] ?: true
        val nightTheme = prefs[ParametersKeys.NIGHT_THEME] ?: false

        return SettingsParameters(useMobileData, language, useDefaultSystemTheme, nightTheme)
    }

    // FUNCIONES PARA SETTINGSDATASTORE
    suspend fun putString(key: String, value: String?) {
        if (value != null) {
            dataStore.edit { preferences ->
                preferences[stringPreferencesKey(key)] = value
            }
        }
    }
    suspend fun getString(key: String, defValue: String?): String {
        return dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)] ?: defValue ?: defValues[key]!!
        }.catch {
            emit(defValue!!)
        }.first()
    }
    suspend fun putBoolean(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }
    suspend fun getBoolean(key: String, defValue: Boolean): Boolean {
        Log.d("DEBUG - SDS","Se va a recuperar un boolean '${key}'")
        return dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(key)] ?: defValue
        }.first()
    }



    /**
     *  Función de acceso al servicio REST
     */
    open class PreferencesException(): Exception() {}   // Excepción inventada para interrumpir
                                                        // el acceso a la API si las preferencias
                                                        // no lo permiten

    fun updateLocationsData() =
        // Se crea un flujo
        flow {
            // Se realiza la petición al servicio
            try {
                // Verificacion de si se ha permitido la descarga de datos con datos moviles en
                // los parametros de la app
                val isDataUseAllowed = fetchParameters().useMobileData
                Log.d("Debug - LRepo", "usMobileData: $isDataUseAllowed")
                if (isDataUseAllowed=="ONLYWIFI" && !isWifiConnected){
                    Log.d("Debug - LRepo", "Se va a cancelar la peticion a la api por no permitir uso de datos")
                    val exception = object : PreferencesException() {
                        override val message: String = "Uso de datos móviles no permitido"
                    }
                    throw exception
                }

                Log.d("Debug - LRepo", "Se va a realizar la peticion a la api")

                // Petición al servicio
                val locations = RestApi.retrofitService.getLocationsInfo()

                // Limpiamos la base de datos
                // La funcion delete tiene que estar despues de la llamada a la api o borraría los datos obsoletos
                // incluso cuando no puede actualizarlos
                deleteLocations()

                // Se guardan los nuevos datos en la base de datos
                locations.items.map { insertLocation(it) }

                // Se emite el estado Succes y se incluyen los datos recibidos
                emit(ApiResult.Success(locations))

            } catch (e: Exception) {
                // Error en la red
                // Se emite el estado de Error con el mensaje que lo explica
                if (e is PreferencesException)
                    emit(ApiResult.Error(e.message!!))
                else
                    emit(ApiResult.Error("Error en el acceso a la API"))
            }

            // El flujo se ejecuta en el hilo I/O
        }.flowOn(Dispatchers.IO)
}