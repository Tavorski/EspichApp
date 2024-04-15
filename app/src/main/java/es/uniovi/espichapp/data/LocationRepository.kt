package es.uniovi.espichapp.data

import android.util.Log
import es.uniovi.arqui.model.LocationDAO
import es.uniovi.espichapp.model.Location
import es.uniovi.espichapp.network.RestApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocationRepository(private val locationDAO: LocationDAO) {


    fun getLocations() = locationDAO.getLocations()

    fun getLocationByName(locationname: String): Flow<Location> {
        return locationDAO.getLocationByName(locationname)
    }

    suspend fun insertLocation(location: Location) {
        Log.d("DEBUG","Llamada a dao ")
        locationDAO.insertLocation(location)
    }


    fun updateBusStatusData() =
        // Se crea un flujo
        flow {
            // Se realiza la petición al servicio
            try {
                // Respuesta correcta
                val busStatus = RestApi.retrofitService.getStatusInfo()
                // Se emite el estado Succes y se incluyen los datos recibidos
                emit(ApiResult.Success(busStatus))
            } catch (e: Exception) {
                // Error en la red
                // Se emite el estado de Error con el mensaje que lo explica
                emit(ApiResult.Error(e.toString()))
            }
            // El flujo se ejecuta en el hilo I/O
        }.flowOn(Dispatchers.IO)
}