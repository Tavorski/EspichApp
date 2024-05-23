package es.uniovi.espichapp.domain

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.uniovi.espichapp.data.Filter
import es.uniovi.espichapp.data.Repository
import es.uniovi.espichapp.data.SettingsParameters
import es.uniovi.espichapp.data.UseMobileData
import es.uniovi.espichapp.data.UserPreferences
import es.uniovi.espichapp.data.UserPreferencesKeyStrings
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


private const val TAG = "DEBUG - PrefVM"
/**
 * Este ViewModel gestiona el acceso de las vistas al DataStore que contiene las preferencias de usuario
 * y los ajustes de la aplicacion. Por ejemplo, este ViewModel es accedido por MainActivity para
 * observar los cambios en la configuración y hacer las modificaciones pertinentes, y es accedido por
 * LocationsListFragment y DetailFragment para conocer la configuracion sobre el uso de datos moviles
 * y regular el uso que hacen sus vistas (ViewHolders en estos casos) de la red
 */
class PreferencesViewModel(val repository: Repository): ViewModel() {

    private val _settingsParameters  = MutableLiveData<SettingsParameters>()
    val settingsParameters: LiveData<SettingsParameters> get() = _settingsParameters
    private val _userPreferences  = MutableLiveData<UserPreferences>()
    val userPreferences: LiveData<UserPreferences> get() = _userPreferences

    init {
        collectSettingsParameters()
        collectUserPreferences()
    }

    /**
     * En esta función se conectan los MutableLivedata de ajustes con los datos filtrados del datastore de
     * forma que settingsParametersFlow solo activa a sus observers cuando hay cambios en los ajustes
     */
    private fun collectSettingsParameters() {
        viewModelScope.launch {
            repository.settingsParametersFlow
                .distinctUntilChanged()
                .collect { settingsParameters ->
                    _settingsParameters.postValue(settingsParameters)
                }
        }
    }
    /**
     * En esta función se conecta el MutableLivedata de preferencias con los datos filtrados del datastore de
     * que userPreferencesFlow solo activa a sus observers cuando hay cambios en las preferencias
     */
    private fun collectUserPreferences() {
        viewModelScope.launch {
            repository.userPreferencesFlow
                .distinctUntilChanged()
                .collect { userPreferences ->
                    _userPreferences.postValue(userPreferences)
                }
        }
    }

//
//    Modificaciones del datastore
//
    fun updateIsWifiConnected(context: Context) {
        repository.isWifiConnected = isWifiConnected(context)
    }
    fun updateFilterPreferences(filter: Filter, newValue: Boolean) {
        viewModelScope.launch {
            Log.d(TAG,"Se va a guardar el filtro $filter: $newValue")
            when (filter) {
                Filter.BY_CELLARS -> repository.putBoolean(
                    UserPreferencesKeyStrings.FILTER_BY_CELLARS,
                    newValue
                )

                Filter.BY_CIDERMILLS -> repository.putBoolean(
                    UserPreferencesKeyStrings.FILTER_BY_CIDERMILLS,
                    newValue
                )
                Filter.BY_DAIRIES -> repository.putBoolean(
                    UserPreferencesKeyStrings.FILTER_BY_DAIRIES,
                    newValue
                )
            }
        }
    }


//
//    Consultas al datastore
//
    private fun isWifiConnected(context: Context): Boolean {
        var ret = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            ret = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
        return ret
    }

    // Para cargar imagenes en funcion de las preferencias del uso de datos
    fun areDownloadsAllowed(context: Context): Boolean {
        val isDatauseAllowed = runBlocking {
            repository.fetchSettingsParameters().useMobileData == UseMobileData.ANY.name
        }

        val res = !(!isWifiConnected(context) && !isDatauseAllowed)
        Log.d(TAG, "permiso para pintar imagenes de la lista: $res")
        return res
    }
}