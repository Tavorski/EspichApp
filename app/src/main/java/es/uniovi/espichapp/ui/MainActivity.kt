package es.uniovi.espichapp.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceDataStore
import androidx.preference.PreferenceManager
import es.uniovi.espichapp.R
import es.uniovi.espichapp.dataStore
import es.uniovi.espichapp.databinding.ActivityMainBinding
import es.uniovi.espichapp.preferences.SettingsFragment
import kotlinx.coroutines.flow.map



val Context.dataStore by preferencesDataStore(name="settings")
class MainActivity : AppCompatActivity() {

    // CAMPOS
    private lateinit var binding: ActivityMainBinding

    // OVERRIDES
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadPreferences()

    }

    fun loadPreferences() {
        dataStore.data.map { preferences ->
            if (preferences[booleanPreferencesKey("mobile_data")] == false) {

            }
            else {

            }

        }
    }
}

