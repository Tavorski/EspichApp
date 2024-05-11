package es.uniovi.arqui.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.datastore.core.DataStoreFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import es.uniovi.arqui.domain.LocationListViewModel
import es.uniovi.espichapp.data.LocationRepository
import es.uniovi.espichapp.domain.DetailViewModel
import es.uniovi.espichapp.domain.MainActivityViewModel
import es.uniovi.espichapp.model.Location

class Utils {

    // Funciones

    companion object {
        fun isWifiConnected(context: Context): Boolean{
            var isWifiConnected = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                isWifiConnected = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
            return isWifiConnected
        }
    }

    // Factorías de ViewModels

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

            else if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainActivityViewModel(repository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }



}