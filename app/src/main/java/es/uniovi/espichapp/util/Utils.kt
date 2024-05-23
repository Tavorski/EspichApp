package es.uniovi.arqui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.uniovi.espichapp.domain.LocationsListViewModel
import es.uniovi.espichapp.data.Repository
import es.uniovi.espichapp.domain.DetailViewModel
import es.uniovi.espichapp.domain.PreferencesViewModel

class Utils {


    // Factoría de ViewModels

    class ViewModelFactory(private val repository: Repository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(LocationsListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LocationsListViewModel(repository) as T
            }

            else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailViewModel(repository) as T
            }

            else if (modelClass.isAssignableFrom(PreferencesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PreferencesViewModel(repository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }



}