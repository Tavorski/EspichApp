package es.uniovi.arqui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import es.uniovi.arqui.domain.LocationListViewModel
import es.uniovi.espichapp.data.LocationRepository
import es.uniovi.espichapp.domain.DetailViewModel
import es.uniovi.espichapp.model.Location

class Utils {

    // Factoría de ViewModels
    class LocationViewModelFactory(private val repository: LocationRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LocationListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LocationListViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    class DetailViewModelFactory(private val repository: LocationRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}