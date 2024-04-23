package es.uniovi.espichapp.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import es.uniovi.espichapp.data.LocationRepository
import es.uniovi.espichapp.model.Location

class DetailViewModel(val repository: LocationRepository) : ViewModel() {

    // CAMPOS
    private val locationName = MutableLiveData<String>()
    // Usamos el nombre como trigger para
    val location: LiveData<Location> = locationName.switchMap {
            name -> repository.getLocationByName(name).asLiveData()
    }

    // MÉTODOS
    fun setLocation(name: String) {
        locationName.value = name
    }

}