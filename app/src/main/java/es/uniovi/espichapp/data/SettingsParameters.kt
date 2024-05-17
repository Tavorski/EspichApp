package es.uniovi.espichapp.data

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey


object SettingsParametersKeyStrings {
    const val USE_MOBILE_DATA: String = "mobile_data"
    const val LANGUAGE: String = "language"
    const val USE_DEFAULT_SYSTEM_THEME: String = "system_theme"
    const val NIGHT_THEME: String = "night_theme"
}

enum class UseMobileData{
    ANY,
    ONLYWIFI
}
enum class Language{
    ES,
    EN
}

/**
 * Clase que empaqueta los parametros de ajuste que se extraen del Datastore en el repositorio
 */
data class SettingsParameters(
    val useMobileData: String,
    val language: String,
    val useDefaultSystemTheme: Boolean,
    val nightTheme: Boolean
)
