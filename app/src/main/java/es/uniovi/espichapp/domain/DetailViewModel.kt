package es.uniovi.espichapp.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import es.uniovi.espichapp.data.LocationRepository
import es.uniovi.espichapp.model.Location

class DetailViewModel(val repository: LocationRepository) : ViewModel() {

    // CAMPOS
    lateinit var loc: Location

    // MÉTODOS
    suspend fun setLocation(name: String) {
        Log.d("DEBUG-DVM", "Buscando lugar '$name'")
        loc = repository.getLocationByName(name)
        Log.d("DEBUG-DVM", "Se ha encontrad $name")
    }

    fun getLocation(): Location {
        return loc
    }


}