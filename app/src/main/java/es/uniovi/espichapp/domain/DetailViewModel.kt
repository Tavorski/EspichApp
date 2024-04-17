package es.uniovi.espichapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import es.uniovi.espichapp.data.LocationRepository
import es.uniovi.espichapp.model.Location
import es.uniovi.espichapp.ui.LocationsUIState

class DetailViewModel(val repository: LocationRepository) : ViewModel() {

    val locationName: String
    val location: LiveData<Location> by lazy {
        repository.getLocationByName(locationName).asLiveData()
    }
    private val _locationsUIStateObservable = MutableLiveData<LocationsUIState>()
    val locationsUIStateObservable: LiveData<LocationsUIState> get() = _locationsUIStateObservable


}