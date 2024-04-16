package es.uniovi.arqui.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import es.uniovi.espichapp.data.ApiResult
import es.uniovi.espichapp.data.LocationRepository
import es.uniovi.espichapp.model.Location
import es.uniovi.espichapp.ui.LocationsUIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LocationListViewModel(val Repository: LocationRepository): ViewModel() {

    val locationList: LiveData<List<Location>> = Repository.getLocations().asLiveData()
    private val _locationsUIStateObservable = MutableLiveData<LocationsUIState>()
    val locationsUIStateObservable: LiveData<LocationsUIState> get() = _locationsUIStateObservable

    fun insertLocation(l: Location) {
        CoroutineScope(Dispatchers.Default).launch {
            Repository.insertLocation(l)
        }
    }
   init {
       getLocationsList()
       insertLocation(Location("Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",
           "Ejemplo",))
   }

    fun getLocationsList() {
        viewModelScope.launch {
            Repository.updateLocationsData().map { result ->
                when (result) {
                    is ApiResult.Success -> LocationsUIState.Success(result.data?.items!!)
                    is ApiResult.Error -> {
                        Log.d("DEBUG - LLVM",result.message!!)
                        LocationsUIState.Error("Error en la petición a la API")
                    }
                }
            }.collect {
                _locationsUIStateObservable.value = it
            }
        }
    }

}