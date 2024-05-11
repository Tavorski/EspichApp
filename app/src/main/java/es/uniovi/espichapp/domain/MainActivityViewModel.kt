package es.uniovi.espichapp.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import es.uniovi.espichapp.data.LocationRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(val repository: LocationRepository): ViewModel() {

    val userPreferencesFlow = repository.userParametersFlow.asLiveData()


    fun updateIsWifiConnected(isConnected: Boolean) {
        repository.isWifiConnected = isConnected
    }


}