package es.uniovi.espichapp.domain

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import es.uniovi.arqui.util.Utils
import es.uniovi.espichapp.data.LocationRepository
import es.uniovi.espichapp.data.UseMobileData
import kotlinx.coroutines.runBlocking


private const val TAG = "DEBUG - PrefVM"
/**
 * Este ViewModel gestiona el acceso de las vistas al DataStore que contiene las preferencias de usuario
 * y los ajustes de la aplicacion
 */
class PreferencesViewModel(val repository: LocationRepository): ViewModel() {

    val settingsParameters = repository.settingsParametersFlow.asLiveData()

    fun isWifiConnected(context: Context): Boolean {
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

    fun updateIsWifiConnected(context: Context) {
        repository.isWifiConnected = isWifiConnected(context)
    }

    // Para cargar imagenes en funcion de las preferencias del uso de datos
    fun areDownloadsAllowed(context: Context): Boolean {
        val isDatauseAllowed = runBlocking {
            repository.fetchParameters().useMobileData == UseMobileData.ANY.name
        }

        val res = !(!isWifiConnected(context) && !isDatauseAllowed)
        Log.d(TAG, "permiso para pintar imagenes de la lista: $res")
        return res
    }
}