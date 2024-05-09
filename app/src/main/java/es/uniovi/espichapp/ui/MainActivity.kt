package es.uniovi.espichapp.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import es.uniovi.espichapp.dataStore
import es.uniovi.espichapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    // CAMPOS
    private lateinit var binding: ActivityMainBinding

    // OVERRIDES
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadPreferences()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun loadPreferences() {
        lifecycleScope.launch {
            dataStore.data.collect() { preferences ->
                //preferences[srin]
            }
        }
    }
}

