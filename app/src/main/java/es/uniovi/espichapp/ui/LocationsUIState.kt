package es.uniovi.espichapp.ui

import es.uniovi.espichapp.model.Location
import es.uniovi.espichapp.model.LocationList
import es.uniovi.espichapp.status.AppStatus


sealed class LocationsUIState (val state: AppStatus) {
    data class Success(val datos: List<Location>): LocationsUIState(AppStatus.SUCCESS)
    data class Error (val message:String): LocationsUIState(AppStatus.ERROR)
}