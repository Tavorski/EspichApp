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

private const val TAG = "DEBUG - Repository"
/**
 * Este es el repositorio general de la aplicacion de donde se toman todos los datos que necesitan
 * las vistas. Sus fuentes de datos son: los datos provenientes de Retrofit, una base de datos ROOM
 * en la que se guardan los datos obtenidos a través de Retrofit y un Datastore<Preferences> en el que
 * se almacenan las preferencias de usuario y configuración de la app.
 *
 * El Datastore se encuentra instanciado en la clase EspichApp.kt que hereda de Application
 */
class LocationRepository(private val locationDAO: LocationDAO,
                         private val dataStore: DataStore<Preferences>) {


//
//    CAMPOS
//
    var isWifiConnected: Boolean = false

//
//    Funciones de acceso a la base de datos ROOM
//
    fun getLocations() = locationDAO.getLocations()
    fun searchLocationsByName(query: String) = locationDAO.searchLocationsByName(query)
    fun getLocationByName(locationName: String) = locationDAO.getLocationByName(locationName)
    suspend fun deleteLocations() {
        CoroutineScope(Dispatchers.IO).launch {
            locationDAO.deleteLocations()
        }
        Log.d(TAG,"DELETE locations_table")
    }
    suspend fun insertLocation(location: Location) {
        CoroutineScope(Dispatchers.IO).launch {
            locationDAO.insertLocation(location)
        }
        Log.d(TAG,"INSERT '${location.Nombre}'")
    }

//
//    Funciones de acceso al datastore
//
    private object SettingsParametersKeys {
        val USE_MOBILE_DATA = stringPreferencesKey( SettingsParametersKeyStrings.USE_MOBILE_DATA )
        val LANGUAGE = stringPreferencesKey( SettingsParametersKeyStrings.LANGUAGE )
        val USE_DEFAULT_SYSTEM_THEME = booleanPreferencesKey( SettingsParametersKeyStrings.USE_DEFAULT_SYSTEM_THEME )
        val NIGHT_THEME = booleanPreferencesKey( SettingsParametersKeyStrings.NIGHT_THEME )
    }
    private object UserPreferencesKeys {
        val FILTER_BY_CELLARS = booleanPreferencesKey( UserPreferencesKeyStrings.FILTER_BY_CELLARS )
        val FILTER_BY_CIDERMILLS = booleanPreferencesKey( UserPreferencesKeyStrings.FILTER_BY_CIDERMILLS )
        val FILTER_BY_DAIRIES = booleanPreferencesKey( UserPreferencesKeyStrings.FILTER_BY_DAIRIES )
    }
    private val defValues: Map<String,String> = mapOf(
        Pair("mobile_data","ANY"),
        Pair("language","ES"),
        Pair("system_theme","true"),
        Pair("theme","true"))

    /**
     * Este flow es un subconjunto del flow del Datastore que contiene solo las PARÁMETROS DE CONFIGURACION
     * Para realizar el filtrado, emplea una función mapSettingsParameters que mapea el objeto Preferences
     * del Datastore a una data class llamada SettingsParameters y la emite en un flow
     */
    val settingsParametersFlow: Flow<SettingsParameters> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error al leer los parámetros de configuracion.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            mapSettingsParameters(preferences)
        }
    suspend fun fetchSettingsParameters() = mapSettingsParameters(dataStore.data.first().toPreferences())
    /**
     * Extrae SettingsParameters de un objeto Preferences
     */
    private fun mapSettingsParameters(prefs: Preferences): SettingsParameters {
        val useMobileData = prefs[SettingsParametersKeys.USE_MOBILE_DATA] ?: UseMobileData.ANY.name
        val language = prefs[SettingsParametersKeys.LANGUAGE] ?: Language.ES.name
        val useDefaultSystemTheme = prefs[SettingsParametersKeys.USE_DEFAULT_SYSTEM_THEME] ?: true
        val nightTheme = prefs[SettingsParametersKeys.NIGHT_THEME] ?: false

        return SettingsParameters(useMobileData, language, useDefaultSystemTheme, nightTheme)
    }


    /**
     * Este flow es un subconjunto del flow del Datastore que contiene solo las PREFERENCIAS DE USUARIO
     * Para realizar el filtrado, emplea una función mapUserPreferences que mapea el objeto Preferences
     * del Datastore a una data class llamada UserPreferences y la emite en un flow
     */
    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error al leer las preferencias de usuario.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            mapUserPreferences(preferences)
        }

    suspend fun fetchUserPreferences() = mapUserPreferences(dataStore.data.first().toPreferences())
    /**
     * Extrae UserPreferences de un objeto Preferences
     */
    private fun mapUserPreferences(prefs: Preferences): UserPreferences {
        val filterByCellars = prefs[UserPreferencesKeys.FILTER_BY_CELLARS] ?: false
        val filterByCidermills = prefs[UserPreferencesKeys.FILTER_BY_CIDERMILLS]  ?: false
        val filterByDairies = prefs[UserPreferencesKeys.FILTER_BY_DAIRIES] ?: false

        return UserPreferences(filterByCellars, filterByCidermills, filterByDairies)
    }


//
//    Funciones que usa SettingsDatastore para acceder al Datastore
//
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
            Log.d(TAG,"Se ha guardado el filtro $key: $value")
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
                val isDataUseAllowed = fetchSettingsParameters().useMobileData
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