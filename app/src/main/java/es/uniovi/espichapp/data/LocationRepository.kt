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

    fun getLocationByName(locationname: String): Flow<Location> {
        return locationDAO.getLocationByName(locationname)
    }

    suspend fun insertLocation(location: Location) {
        locationDAO.insertLocation(location)
        Log.d("DEBUG-Repo","INSERT ${location.Nombre}")
    }


    fun updateLocationsData() =
        // Se crea un flujo
        flow {
            // Se realiza la petición al servicio
            var locations: LocationList? = null
            try {
                // Respuesta correcta
                locations = RestApi.retrofitService.getLocationsInfo()
                // Se emite el estado Succes y se incluyen los datos recibidos
                emit(ApiResult.Success(locations))
            } catch (e: Exception) {
                // Error en la red
                // Se emite el estado de Error con el mensaje que lo explica
                emit(ApiResult.Error(e.toString()))
            }
            CoroutineScope(Dispatchers.IO).launch {
                locations!!.items.map {
                    insertLocation(it)
                }
            }
            // El flujo se ejecuta en el hilo I/O
        }.flowOn(Dispatchers.IO)
}