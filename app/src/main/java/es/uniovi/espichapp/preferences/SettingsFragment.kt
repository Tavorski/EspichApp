package es.uniovi.espichapp.preferences

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.datastore.dataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.preference.PreferenceFragmentCompat
import es.uniovi.espichapp.R
import es.uniovi.espichapp.ui.dataStore
import java.util.prefs.PreferenceChangeEvent
import java.util.prefs.PreferenceChangeListener


class SettingsFragment : PreferenceFragmentCompat(), PreferenceChangeListener {

    // CAMPOS

    // OVERRIDES
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun preferenceChange(evt: PreferenceChangeEvent?) {
        if (evt != null) {
            with(evt.key) {
                is "mobile_data"

                is
            }
        }
    }
}