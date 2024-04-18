package es.uniovi.espichapp.data

import android.util.Log
import es.uniovi.arqui.model.LocationDAO
import es.uniovi.espichapp.model.Location
import es.uniovi.espichapp.model.LocationList
import es.uniovi.espichapp.network.RestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class LocationRepository(private val locationDAO: LocationDAO) {


    fun getLocations() = locationDAO.getLocations()

    suspend fun getLocationByName(locationname: String): Location {
        return locationDAO.getLocationByName(locationname)
    }

    suspend fun insertLocation(location: Location) {
        locationDAO.insertLocation(location)
        Log.d("DEBUG-Repo","INSERT '${location.Nombre}'")
    }


    fun updateLocationsData() =
        // Se crea un flujo
        flow {
            // Se realiza la petición al servicio
            try {
                // Respuesta correcta
                val locations = RestApi.retrofitService.getLocationsInfo()
                // Se guardan los nuevos datos en la base de datos
                CoroutineScope(Dispatchers.IO).launch {
                    locations.items.map {
                        insertLocation(it)
                    }
                }
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