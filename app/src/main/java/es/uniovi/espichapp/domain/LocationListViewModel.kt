package es.uniovi.arqui.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import es.uniovi.espichapp.data.ApiResult
import es.uniovi.espichapp.data.LocationRepository
import es.uniovi.espichapp.data.UseMobileData
import es.uniovi.espichapp.model.Location
import es.uniovi.espichapp.ui.LocationsUIState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LocationListViewModel(val repository: LocationRepository): ViewModel() {


    // CAMPOS
    private val _locationsUIStateObservable = MutableLiveData<LocationsUIState>()
    val locationsUIStateObservable: LiveData<LocationsUIState> get() = _locationsUIStateObservable

    val query = MutableLiveData<String>()
    private val locationsByName: LiveData<List<Location>> = query.switchMap {
        name -> repository.searchLocationsByName(name).asLiveData()
    }

    // Este MediatorLiveData está pensado para que escuche el livedata de las búsquedas, le aplique
    // un filtro cuando detecte cambios y luego ser observado en el fragmento
    val filter: BooleanArray = booleanArrayOf(false,false,false) // "por bodegas", "por llagares", "por queserias"
    val locationsFilteredSearch: MediatorLiveData<List<Location>> = MediatorLiveData()


    // Inicializacion del viewmodel
    init {
        query.value = ""
        // A continuacion, se implementa un observador que filtra la lista resultado de la búsqueda
        // y que retorna una lista en el MediatorLiveData que será la lista observada por la UI
        //
        // En este observador se indica que:
        // -Si no hay ninguna checkbox marcada en el filtro la lista se muestra entera
        // -Si hay alguna checkbox marcada, se muestra solo parte de la lista en funcion de los tipos
        //  de establecimiento
        locationsFilteredSearch.addSource(locationsByName) { list ->
            if (!filter.contains(true)) locationsFilteredSearch.value = list
            else
                locationsFilteredSearch.value = list.filter {
                    it.isBodega() && filter[0]
                            || it.isLlagar() && filter[1]
                            || it.isQueseria() && filter[2]
                }
        }
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
                            LocationsUIState.Error(result.message)
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
