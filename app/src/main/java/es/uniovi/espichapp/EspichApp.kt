package es.uniovi.espichapp

import android.app.Application
import es.uniovi.espichapp.data.LocationRepository
import es.uniovi.espichapp.model.Location
import es.uniovi.espichapp.model.LocationDatabase

class EspichApp: Application() {

    val courseDatabase by lazy {
        LocationDatabase.getInstance(this)
    }
    val repository by lazy {
        LocationRepository(courseDatabase!!.locationDao())
    }
}