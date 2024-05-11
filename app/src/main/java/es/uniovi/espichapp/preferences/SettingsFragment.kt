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


class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {

    // CAMPOS

    // OVERRIDES
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.preferenceDataStore = SettingsDataStore((activity?.application as EspichApp).repository)
        setPreferencesFromResource(R.xml.preferences, rootKey)
        //findPreference<ListPreference>("mobile_data")?.onPreferenceChangeListener = this
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
        Log.d("DEBUG - SF","Se han cambiado preferencias")
        val key = preference.key
        val new = newValue.toString()
        when(preference.key) {
            "mobile_data" -> {
                Log.d("DEBUG - SF","Se ha cambiado el valor 'mobile_data'")
            }
            "language" -> {
                Log.d("DEBUG - SF","Se ha cambiado el valor 'language'")
                val config = resources.configuration
                val lang: String = new // your language code
                val locale = Locale(lang)
                Locale.setDefault(locale)
                config.setLocale(locale)
            }
            else -> preferenceManager.preferenceDataStore?.putString(key,new)
        }
        return true
    }
}