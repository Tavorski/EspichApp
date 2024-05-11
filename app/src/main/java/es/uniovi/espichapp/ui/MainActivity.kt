package es.uniovi.espichapp.ui



import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import es.uniovi.arqui.util.Utils
import es.uniovi.espichapp.EspichApp
import es.uniovi.espichapp.data.UseMobileData
import es.uniovi.espichapp.dataStore
import es.uniovi.espichapp.databinding.ActivityMainBinding
import es.uniovi.espichapp.domain.MainActivityViewModel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    // CAMPOS
    private lateinit var binding: ActivityMainBinding
    private val mainActivityVM: MainActivityViewModel by viewModels {
        Utils.ViewModelFactory((application as EspichApp).repository)
    }

    // OVERRIDES
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setWifiStatus()
        loadPreferences()

        mainActivityVM.userPreferencesFlow.observe(this) { parameters ->
            Log.d("DEBUG - Main","Se han detectado cambios en dataStore")
            if (parameters.useMobileData != UseMobileData.ANY.name) {
                setWifiStatus()
            }
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun setWifiStatus() {
        val wifistatus = mainActivityVM.updateIsWifiConnected(Utils.isWifiConnected(applicationContext))

        Log.d("DEBUG - Main","isWifiConnected: $wifistatus")
    }

    fun loadPreferences() {
        lifecycleScope.launch {
            dataStore.data.collect() { preferences ->
                preferences[stringPreferencesKey("mobile_data")]
            }
        }
    }
}

