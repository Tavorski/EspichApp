package es.uniovi.espichapp.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import es.uniovi.espichapp.R
import es.uniovi.espichapp.databinding.ActivityMainBinding

class MainActivity :
    AppCompatActivity(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    //private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onStart() {
        super.onStart()
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }
    override fun onPause() {
        super.onPause()
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // CONFIGURACION DE LAS PREFERENCIAS
        // Valor por defecto en la primera ejecución
        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        PreferenceManager.setDefaultValues(this,"prefs",Context.MODE_PRIVATE,R.xml.preferences,false)
        /*

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        */
        /*binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/


    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        var mi: MenuItem? = menu.findItem(R.id.searchView)
        var sv: SearchView = mi?.actionView as SearchView
        sv.queryHint = getString(R.string.action_search_hint)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.settings -> NavigationUI.onNavDestinationSelected(item, navController)
            else -> super.onOptionsItemSelected(item)
        }
    }*/



    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) { }


    /*override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }*/
}

