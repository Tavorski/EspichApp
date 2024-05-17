package es.uniovi.arqui.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.uniovi.arqui.domain.LocationListViewModel
import es.uniovi.espichapp.data.LocationRepository
import es.uniovi.espichapp.domain.DetailViewModel
import es.uniovi.espichapp.domain.PreferencesViewModel

class Utils {


    // Factoría de ViewModels

    class ViewModelFactory(private val repository: LocationRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(LocationListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LocationListViewModel(repository) as T
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