package es.uniovi.espichapp.ui



import android.app.LocaleConfig
import android.app.LocaleManager
import android.content.Context
import android.os.Build
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
import java.util.Locale

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
        preferencesVM.settingsParameters.observe(this) { parameters ->
            Log.d("DEBUG - Main","Se han detectado cambios en dataStore")
            if (parameters.useMobileData != UseMobileData.ANY.name) {
                setWifiStatus()
            }

            // Cambiamos el idioma
            setLanguage(parameters.language)

            // Cambiamos el tema
            setAppTheme(parameters.useDefaultSystemTheme,parameters.nightTheme)

        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

