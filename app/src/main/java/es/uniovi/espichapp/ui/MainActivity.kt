package es.uniovi.espichapp.ui



import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import es.uniovi.arqui.util.Utils
import es.uniovi.espichapp.EspichApp
import es.uniovi.espichapp.data.UseMobileData
import es.uniovi.espichapp.databinding.ActivityMainBinding
import es.uniovi.espichapp.domain.PreferencesViewModel

private const val TAG = "DEBUG - MainAct"

class MainActivity : AppCompatActivity() {

    // CAMPOS
    private lateinit var binding: ActivityMainBinding
    private val preferencesVM: PreferencesViewModel by viewModels {
        Utils.ViewModelFactory((application as EspichApp).repository)
    }

    // OVERRIDES
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // hacemos que se observe el subconjunto del datastore que contiene los parámetros de la app
        // para que se produzcan los cambios pertinentes en la aplicacion
        observeSettingsParameters()
        // y lo mismo con las preferencias de usuario (no lo hacemos porque solo existen como preferencias
        // de usuario los tipos de establecimiento elegidos en el filtro
        //observeUserPreferences()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    /**
     * Inicializa aquellos componentes de la aplicacion afectados por PARAMETROS DE AJUSTE,
     * como el uso de datos moviles, el modo noche y el idioma
     */
    private fun observeSettingsParameters() {
        preferencesVM.settingsParameters.observe(this) { parameters ->
            Log.d("DEBUG - Main","Se han detectado cambios en los ajustes")
            // Si el uso de datos móviles está restringido, lo indicamos en el repositorio
            // para evitar descargas de datos
            if (parameters.useMobileData != UseMobileData.ANY.name) {
                setWifiStatus()
            }
            // Cambiamos el idioma
            setLanguage(parameters.language)
            // Cambiamos el tema
            setAppTheme(parameters.useDefaultSystemTheme,parameters.nightTheme)
        }
    }
    /**
     * Inicializa aquellos componentes de la aplicacion afectados por PREFERENCIAS DE USUARIO,
     * como el filtro de la lista por tipo de establecimiento (bodega, llagar, quesería)
     */
    private fun observeUserPreferences() {
        //preferencesVM.userPreferences.observe(this) {        }
    }

    /**
     * Cambia el idioma en el que se muestra la aplicacion
     */
    private fun setLanguage(lang: String) {
        Log.d(TAG,"Se va a cambiar el idioma a $lang")

        AppCompatDelegate.setApplicationLocales( // cambia el idioma
            LocaleListCompat.forLanguageTags(lang)
        )
        Log.d(TAG,"Se ha cambiado el idioma a $lang")
    }

    /**
     * Cambia el tema en el que se muestra la aplicacion
     */
    private fun setAppTheme(systemTheme: Boolean, nightMode: Boolean) {
        if (systemTheme)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        else if (nightMode)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    /**
     * Fuerza al ViewModel a comprobar si el dispositivo está conectado a una red wifi
     * y guarda el dato en el repositorio para que en él se pueda tener en cuenta
     * el tipo de red en uso a la hora de descargar datos a través de Retrofit
     */
    private fun setWifiStatus() {
        val wifistatus = preferencesVM.updateIsWifiConnected(applicationContext)

        Log.d(TAG,"isWifiConnected: $wifistatus")
    }

}

