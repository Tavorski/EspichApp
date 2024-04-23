package es.uniovi.espichapp.data

import android.util.Log
import androidx.lifecycle.asLiveData
import es.uniovi.arqui.model.LocationDAO
import es.uniovi.espichapp.model.Location
import es.uniovi.espichapp.network.RestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class LocationRepository(private val locationDAO: LocationDAO) {

    // Funciones de acceso a la base de datos ROOM
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

    // Función de acceso al servicio REST
    fun updateLocationsData() =
        // Se crea un flujo
        flow {
            // Se realiza la petición al servicio
            try {// Respuesta correcta

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
                emit(ApiResult.Error(e.toString()))
            }

            // El flujo se ejecuta en el hilo I/O
        }.flowOn(Dispatchers.IO)
}