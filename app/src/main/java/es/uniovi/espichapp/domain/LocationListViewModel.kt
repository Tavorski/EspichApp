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
import es.uniovi.espichapp.model.LocationList
import es.uniovi.espichapp.ui.LocationsUIState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LocationListViewModel(val repository: LocationRepository): ViewModel() {


    val locationsFromDB: LiveData<List<Location>> get() = _locationsFromDB
    private val _locationsFromDB = MutableLiveData<List<Location>>()

    val locationsUIStateObservable: LiveData<LocationsUIState> get() = _locationsUIStateObservable
    private val _locationsUIStateObservable = MutableLiveData<LocationsUIState>()

    init {
        loadLocationsList()
    }


    // MÉTODOS
    fun loadLocationsList() {
        viewModelScope.launch {
            repository.loadList().collect {
                if (it != null) {
                    Log.d("DEBUG - LLVM","Tamaño de la lista en LOAD: ${it.size}")
                }
                _locationsFromDB.value = it
            }
        }
    }

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
