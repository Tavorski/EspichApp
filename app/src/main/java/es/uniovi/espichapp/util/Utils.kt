package es.uniovi.arqui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import es.uniovi.arqui.domain.LocationListViewModel
import es.uniovi.espichapp.data.LocationRepository
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
    companion object DIFF_CALLBACK: DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.name == newItem.name
        }
        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }
    }
}