package es.uniovi.espichapp.preferences

import android.net.ConnectivityManager
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.preference.PreferenceDataStore
import es.uniovi.espichapp.data.LocationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SettingsDataStore(private val repository: LocationRepository): PreferenceDataStore() {


    override fun putString(key: String, value: String?) {
        Log.d("DEBUG - SDS","Se va a guardar un string '$key:$value'")
        CoroutineScope(Dispatchers.IO).launch {
            repository.putString(key,value)
        }
    }
    override fun getString(key: String, defValue: String?): String {
        Log.d("DEBUG - SDS","Se va a recuperar un string '$key'")
        return runBlocking {
            repository.getString(key,defValue)
        }
        /*return runBlocking {
            dataStore.data.map { preferences ->
                preferences[stringPreferencesKey(key)] ?: defValues[key]!!
            }.catch {
                emit(defValue!!)
            }.first()
        }*/
    }
    override fun putBoolean(key: String, value: Boolean) {
        Log.d("DEBUG - SDS","Se va a guardar un boolean '$key:$value'")
        CoroutineScope(Dispatchers.IO).launch {
            repository.putBoolean(key,value)
        }
    }
    override fun getBoolean(key: String, defValue: Boolean): Boolean {
        Log.d("DEBUG - SDS","Se va a recuperar un boolean '${key}'")
        return runBlocking {
            repository.getBoolean(key, defValue)
        }
    }


}