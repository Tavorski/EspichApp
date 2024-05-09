package es.uniovi.espichapp.preferences

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.preference.PreferenceDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SettingsDataStore(private val dataStore: DataStore<Preferences>): PreferenceDataStore() {

    override fun putString(key: String, value: String?) {
        Log.d("DEBUG - SDS","Se va a guardar un string'")
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { preferences ->
                preferences[stringPreferencesKey(key)] = value!!
            }
        }
    }

    override fun getString(key: String, defValue: String?): String {
        Log.d("DEBUG - SDS","Se va a recuperar un string'")
        return runBlocking {
            dataStore.data.map { preferences ->
                preferences[stringPreferencesKey(key)] ?: defValue!!
            }.catch {
                emit(defValue!!)
            }.first()
        }
    }


    override fun putBoolean(key: String, value: Boolean) {
        Log.d("DEBUG - SDS","Se va a guardar un boolean'")
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { preferences ->
                preferences[booleanPreferencesKey(key)] = value
            }
        }
    }

    override fun getBoolean(key: String, defValue: Boolean): Boolean {
        Log.d("DEBUG - SDS","Se va a recuperar un boolean'")
        return runBlocking {
            dataStore.data.map { preferences ->
                preferences[booleanPreferencesKey(key)] ?: defValue
            }.first()
        }
    }


}