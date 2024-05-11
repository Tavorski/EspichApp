package es.uniovi.espichapp.data

enum class UseMobileData{
    ANY,
    ONLYWIFI
}
enum class Language{
    ES,
    EN
}
data class SettingsParameters(
    val useMobileData: String,
    val language: String,
    val useDefaultSystemTheme: Boolean,
    val nightTheme: Boolean
)
