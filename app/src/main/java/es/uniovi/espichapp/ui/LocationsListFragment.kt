package es.uniovi.espichapp.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import es.uniovi.arqui.adapters.LocationListAdapter
import es.uniovi.arqui.domain.LocationListViewModel
import es.uniovi.arqui.util.Utils
import es.uniovi.espichapp.EspichApp
import es.uniovi.espichapp.R
import es.uniovi.espichapp.databinding.FragmentLocationsListBinding
import es.uniovi.espichapp.interfaces.LocationListEvent


class LocationsListFragment :
    Fragment(),
    LocationListEvent,
    SharedPreferences.OnSharedPreferenceChangeListener,
    SwipeRefreshLayout.OnRefreshListener,
    SearchView.OnQueryTextListener {

    // CAMPOS

    // Binding
    private var _binding: FragmentLocationsListBinding? = null
    private val binding get() = _binding!!

    // Para la navegacion entre fragmentos
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    // RecyclerView, Adapter, ViewModel
    lateinit var rvlist: RecyclerView
    private val adapterList: LocationListAdapter = LocationListAdapter(this)
    private val locationsListVM: LocationListViewModel by viewModels {
        Utils.LocationViewModelFactory((activity?.application as EspichApp).repository)
    }

    // Para actualizar al scrollear hacia arriba
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    // Para las preferencias
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // init de las referencias al navHostFragment
        navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
    }
    // OVERRIDES
    override fun onPause() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
        super.onPause()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLocationsListBinding.inflate(inflater, container, false)
        Log.d("DEBUG - LLF","Se ha creado el binding del listfragment")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Modificamos la toolbar según las necesidades de esta vista
        val mainActivity: MainActivity = requireActivity() as MainActivity
        mainActivity.setSupportActionBar(binding.toolbar)
        mainActivity.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.menu_main, menu)
                var mi: MenuItem? = menu.findItem(R.id.searchView)
                var sv: SearchView = mi?.actionView as SearchView
                sv.queryHint = getString(R.string.action_search_hint)

                // La idea es usar MainActivity como listener para que cada vez que se escriba
                // o se modifique la busqueda en el searchview, se modifiquen las preferencias
                // y que LocationListFragment capture el evento de cambio en las preferencias
                sv.setOnQueryTextListener(this@LocationsListFragment)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.settings -> {
                        NavigationUI.onNavDestinationSelected(menuItem, navController)
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        // Asignamos un LayoutManager al recyclerView
        rvlist = binding.rvLocationList
        rvlist.layoutManager = LinearLayoutManager(this.context)
        // Inicializacion del combo adapter-recyclerview
        rvlist.adapter = adapterList
        // El recycler tiene tamaño fijo, luego activamos esta propiedad
        rvlist.setHasFixedSize(true)
        // Guardamos una referencia al RefreshLayout y declaramos este fragmento como listener del refresh
        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this)

        // CONFIGURACION DE LAS PREFERENCIAS Y REGISTRO DEL LISTENER
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(binding.root.context)
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)

        // Hacemos que se observen elementos en la base de datos que se puedan presentar, se cargen en el adapter
        locationsListVM.locations.observe(viewLifecycleOwner) { locList ->
            Log.d("DEBUG - LLF", "Se han observado cambios en locations")
            adapterList.submitList(locList)

        }
        locationsListVM.locationsByName.observe(viewLifecycleOwner) { locList ->
            Log.d("DEBUG - LLF", "Se han observado cambios en locationsByName")
            adapterList.submitList(locList)
        }

        // Una vez inicializada por primera vez la lista de establecimientos, será este observable
        // el que indique la llegada de nuevos datos
        locationsListVM.locationsUIStateObservable.observe(viewLifecycleOwner) { result ->
            when (result) {
                is LocationsUIState.Success -> {

                    //adapterList.submitList(result.datos.items)
                    //locationsListVM.searchList = adapterList.currentList

                    Snackbar.make(view, "Se han observado cambios", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
                is LocationsUIState.Error -> {

                    Snackbar.make(view, result.message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            // hacemos que se quite la animacion circular de carga
            swipeRefreshLayout.isRefreshing = false
        }



        /*binding.btActualizar.setOnClickListener {
            locationVM.getLocationsList()
        }*/


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Esta funcion se llama desde LocationListViewHolder para implementar el evento onClick de
    // los items de la lista
    override fun onItemClick(position: Int) {
        Log.d("DEBUG-ViewHolder", "Se va a intentar navegar al fragmento")
        val locationName: String = locationsListVM.locations.value?.get(position)?.Nombre ?: ""
        if (locationName == "") return
        findNavController().navigate(
             LocationsListFragmentDirections.actionLocationsListFragmentToDetailFragment(locationName)
        )

    }

    // Esta función implementa la recarga de los datos al scrollear hacia arriba (un swipe refresh)
    override fun onRefresh() = locationsListVM.getLocationsList()


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        Log.d("DEBUG - LLF", "Se han cambiado las preferencias '${key}'")
        when (key) {

        }
    }


    // no queremos que haga falta hacer submit de la busqueda
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }
    // con escribir en el searchview es suficiente
    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d("DEBUG - MA", "Se han observado cambios en el searchview")

        if (newText != null) {
            val temp: List<String> = newText.chunked(1)
            val exclude: List<String> = listOf<String>("a","e","i","o","u")
            var query = ""
            for (c in temp) {
                if(exclude.contains(c)) continue // Ignoramos vocales para evitar problemas con las tildes
                query += "%${c}"    // Insertamos un % entre cada caracter para reducir la sensibilidad
                                    // de la búsqueda y que acepte faltas de ortografía
            }
            Log.d("DEBUG - DVM","Query: $query")
            locationsListVM.query.value = query
        }
        else locationsListVM.query.value = ""
        return true
    }





}