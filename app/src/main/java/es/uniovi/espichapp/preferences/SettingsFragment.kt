package es.uniovi.espichapp.preferences


import android.os.Bundle
import android.util.Log
import androidx.datastore.preferences.preferencesDataStore
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import es.uniovi.espichapp.EspichApp
import es.uniovi.espichapp.R
import es.uniovi.espichapp.dataStore
import java.util.Locale


private const val TAG = "DEBUG - SF"

class SettingsFragment : PreferenceFragmentCompat() {

    // CAMPOS

    // OVERRIDES
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.preferenceDataStore = SettingsDataStore((activity?.application as EspichApp).repository)
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

}