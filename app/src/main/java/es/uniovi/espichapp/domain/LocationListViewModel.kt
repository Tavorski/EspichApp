package es.uniovi.arqui.domain

import android.database.Observable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import es.uniovi.espichapp.data.ApiResult
import es.uniovi.espichapp.data.LocationRepository
import es.uniovi.espichapp.model.Location
import es.uniovi.espichapp.ui.LocationsUIState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LocationListViewModel(val repository: LocationRepository): ViewModel() {


    // CAMPOS

    val locations: LiveData<List<Location>> = repository.getLocations().asLiveData()
    val locationsUIStateObservable: LiveData<LocationsUIState> get() = _locationsUIStateObservable
    private val _locationsUIStateObservable = MutableLiveData<LocationsUIState>()

    val query = MutableLiveData<String>()
    val locationsByName: LiveData<List<Location>> = query.switchMap {
        name -> repository.searchLocationsByName(name).asLiveData()
    }

    // MÉTODOS

    fun getLocationsList() {
        viewModelScope.launch {
            try {
                repository.updateLocationsData().map { result ->
                    when (result) {
                        is ApiResult.Success -> {
                            Log.d("DEBUG - LLVM", "ApiResult es Success")
                            LocationsUIState.Success(result.data!!)
                        }

                        is ApiResult.Error -> {
                            Log.d("DEBUG - LLVM", result.message!!)
                            LocationsUIState.Error("Error en la petición a la API")
                        }
                    }
                }.collect {
                    _locationsUIStateObservable.value = it
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (_: Exception) {
            }
        }
    }

}
