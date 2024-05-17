package es.uniovi.espichapp.data


object UserPreferencesKeyStrings {
    const val FILTER_BY_CELLARS: String = "filter_by_cellars"
    const val FILTER_BY_CIDERMILLS: String = "filter_by_cidermills"
    const val FILTER_BY_DAIRIES: String = "filter_by_dairies"
}
enum class Filter {
    BY_CELLARS,
    BY_CIDERMILLS,
    BY_DAIRIES
}

/**
 * Clase que empaqueta las preferencias de usuario que se extraen del Datastore en el repositorio
 */
data class UserPreferences(
    val filterByCellars: Boolean,
    val filterByCidermills: Boolean,
    val filterByDairies: Boolean
)