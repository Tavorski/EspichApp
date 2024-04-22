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

    // CAMPOS
    lateinit var query: String // para guardar la cadena por la que se está buscando en el searchview

    lateinit var filteredList: List<Location>
    var locationsFromDB: LiveData<List<Location>> = repository.getLocationsFlow().asLiveData()
    private val _locationsUIStateObservable = MutableLiveData<LocationsUIState>()
    val locationsUIStateObservable: LiveData<LocationsUIState> get() = _locationsUIStateObservable

    init {
        //getLocationsList()
    }




    // MÉTODOS
    fun search() {
        //locationsFromDB = repository.searchLocationsByName(query).asLiveData()

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
            } catch (_: Exception) {}
        }
    }

}