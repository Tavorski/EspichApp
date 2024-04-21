package es.uniovi.espichapp.preferences

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import es.uniovi.espichapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}