package es.uniovi.espichapp.data

import java.io.Serializable

/**
 * Esta data class se utiliza para pasar las coordenadas de un establecimiento desde DetailFragment
 * a MapFragment mediante SafeArgs
 */
data class LocationCoordinates (
    var locationName: String,
    var address: String,
    var latitude: Double,
    var longitude: Double
): Serializable
