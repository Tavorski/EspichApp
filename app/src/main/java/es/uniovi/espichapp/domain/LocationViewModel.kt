package es.uniovi.arqui.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import es.uniovi.espichapp.data.LocationRepository
import es.uniovi.espichapp.model.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationViewModel(val repository: LocationRepository): ViewModel() {

    val locationNames: LiveData<List<String>> = repository.getLocationNames().asLiveData()

   /* fun insertLocation(l: Location) {
        CoroutineScope(Dispatchers.Default).launch {
            repository.insertLocation(l)
        }
    }*/

}