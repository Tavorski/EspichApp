package es.uniovi.espichapp.data

import android.util.Log
import es.uniovi.arqui.model.LocationDAO
import es.uniovi.espichapp.model.Location
import kotlinx.coroutines.flow.Flow

class LocationRepository(private val locationDAO: LocationDAO) {


    fun getLocationNames() = locationDAO.getNames()

    fun getLocationByName(locationname: String): Flow<Location> {
        return locationDAO.getLocationByName(locationname)
    }

    suspend fun insertLocation(location: Location) {
        Log.d("DEBUG","Llamada a dao ")
        locationDAO.insertLocation(location)
    }
}