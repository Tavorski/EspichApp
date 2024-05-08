package es.uniovi.espichapp

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import es.uniovi.espichapp.data.LocationRepository
import es.uniovi.espichapp.model.Location
import es.uniovi.espichapp.model.LocationDatabase

val Context.dataStore by preferencesDataStore(name="settings")
class EspichApp: Application() {

    val locationsDatabase by lazy {
        LocationDatabase.getInstance(this)
    }
    val repository by lazy {
        LocationRepository(locationsDatabase!!.locationDao(), dataStore)
    }
}