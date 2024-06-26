package es.uniovi.espichapp.ui

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
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import es.uniovi.arqui.adapters.LocationListAdapter
import es.uniovi.espichapp.domain.LocationsListViewModel
import es.uniovi.arqui.util.Utils
import es.uniovi.espichapp.EspichApp
import es.uniovi.espichapp.R
import es.uniovi.espichapp.data.Filter
import es.uniovi.espichapp.databinding.FragmentLocationsListBinding
import es.uniovi.espichapp.domain.PreferencesViewModel
import es.uniovi.espichapp.interfaces.NetworkUseController
import es.uniovi.espichapp.interfaces.OnListItemClickListener


private const val TAG = "DEBUG - LLF"

class LocationsListFragment :
    Fragment(),
    NetworkUseController,
    OnListItemClickListener,
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
    private lateinit var rvlist: RecyclerView
    private lateinit var adapterList: LocationListAdapter
    private val locationsListVM: LocationsListViewModel by viewModels {
        Utils.ViewModelFactory((activity?.application as EspichApp).repository)
    }
    private val preferencesVM: PreferencesViewModel by viewModels {
        Utils.ViewModelFactory((activity?.application as EspichApp).repository)
    }

    // Para actualizar al scrollear hacia arriba
    lateinit var swipeRefreshLayout: SwipeRefreshLayout


    // OVERRIDES
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // init de las referencias al navHostFragment
        navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLocationsListBinding.inflate(inflater, container, false)
        Log.d(TAG,"Se ha creado el binding del listfragment")
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Modificamos la toolbar según las necesidades de esta vista
        initToolbar()

        // Asignamos un LayoutManager al recyclerView
        rvlist = binding.rvLocationList
        rvlist.layoutManager = LinearLayoutManager(this.context)
        // Inicializacion del combo adapter-recyclerview
        adapterList = LocationListAdapter(this,this)
        rvlist.adapter = adapterList
        // El recycler tiene tamaño fijo, luego activamos esta propiedad
        rvlist.setHasFixedSize(true)
        // Guardamos una referencia al RefreshLayout y declaramos este fragmento como listener del refresh
        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this)

        // que se observen las preferencias de usuario
        observeUserPreferences()

        // que se observen los livedata del modelo
        observeFilteredSearchList()
        observeUIStateList(view)

    }

    /*override fun onStart() {
        super.onStart()
        // Forzamos que se resetée la lista cuando se retorna a esta pantalla
        locationsListVM.query.value = ""
    }*/
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // IMPLEMENTACION DE INTERFAZ: OnRefreshListener
    //
    // Esta función implementa la recarga de los datos al scrollear hacia arriba (un swipe refresh)
    override fun onRefresh() = locationsListVM.getLocationsList()

    // IMPLEMENTACION DE INTERFAZ: OnQueryTextListener
    //
    // No queremos que haga falta hacer submit de la busqueda
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }
    // Con escribir en el searchview es suficiente
    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d(TAG, "Se han observado cambios en el searchview")

        if (newText != null)
            locationsListVM.query.value = newText
        else
            locationsListVM.query.value = ""

        return true
    }

    // IMPLEMENTACION DE INTERFAZ: LocationListEvent
    //
    // Esta funcion se llama desde LocationListViewHolder para implementar el evento onClick de
    // los items de la lista
    override fun onItemClick(position: Int) {
        Log.d(TAG, "Se va a intentar navegar al fragmento")
        val locationName: String = locationsListVM.locationsFilteredSearch.value?.get(position)?.Nombre ?: ""
        if (locationName == "") return
        findNavController().navigate(
            LocationsListFragmentDirections.actionLocationsListFragmentToDetailFragment(locationName)
        )
    }

    // IMPLEMENTACION DE INTERFAZ: LocationListEvent
    //
    // Esta funcion se llama desde LocationListViewHolder para indicar al ViewHolder si puede
    // descargar imagenes
    override fun areDownloadsAllowed() = preferencesVM.areDownloadsAllowed(requireContext())



    // FUNCIONES PRIVADAS (INICIALIZACIONES)
    /**
     * Inicializa aquellos componentes de esta vista afectados por PREFERENCIAS DE USUARIO,
     * como el filtro de la lista por tipo de establecimiento (bodega, llagar, quesería)
     */
    private fun observeUserPreferences() {
        preferencesVM.userPreferences.observe(viewLifecycleOwner) { preferences ->
            Log.d(TAG,"Se han detectado cambios en las preferencias de usuario")

            with(preferences.filterByCellars) {
                locationsListVM.filter[0] = this
                binding.cbCellars.isChecked = this
            }
            with(preferences.filterByCidermills) {
                locationsListVM.filter[1] = this
                binding.cbCidermills.isChecked = this
            }
            with(preferences.filterByDairies) {
                locationsListVM.filter[2] = this
                binding.cbDairies.isChecked = this
            }
            // activamos el trigger
            locationsListVM.query.value = locationsListVM.query.value
        }
    }
    private fun observeFilteredSearchList() {
        locationsListVM.locationsFilteredSearch.observe(viewLifecycleOwner) { locList ->
            Log.d(TAG, "Se han observado cambios en locationsFilteredSearch")
            adapterList.submitList(locList)
        }
    }

    /**
     * Este observer solo se usa para mostrar el snackbar con el mensaje de error en el acceso
     * al servicio REST mediante Retrofit si se diese la situación, pero no se submitea al adapter
     * desde aquí.
     */
    private fun observeUIStateList(view: View) {
        locationsListVM.locationsUIStateObservable.observe(viewLifecycleOwner) { result ->
            when (result) {
                is LocationsUIState.Success -> {
                    Log.d(TAG,"Se han observado cambios en locationsUIStateObservable")
                }
                is LocationsUIState.Error -> {
                    Snackbar.make(view, result.message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            // hacemos que se quite la animacion circular de carga
            swipeRefreshLayout.isRefreshing = false
        }
    }

    /**
     * inicializa la appbar y elementos asociados (searchview, filterview)
     */
    private fun initToolbar() {
        val mainActivity: MainActivity = requireActivity() as MainActivity
        mainActivity.setSupportActionBar(binding.toolbar)
        mainActivity.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.menu_main, menu)
                val mi: MenuItem? = menu.findItem(R.id.searchActionView)
                val sv: SearchView = mi?.actionView as SearchView
                sv.queryHint = getString(R.string.action_search_hint)
                // que se vuelva a poner la query en la view desde el modelo
                sv.setQuery(locationsListVM.query.value, false)

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
                    R.id.filterActionView -> {
                        binding.filterlayout.isGone = binding.filterlayout.isGone.not()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        // aquí se hace invisible el despegable con los filtros y se crean los listeners
        binding.filterlayout.isGone = true

        // Aqui usamos el evento onClick en vez de onChecked porque queremos que estos manejadores
        // se ejecuten solo cuando el usuario marca las casillas y no cuando las casillas se marcan
        // de forma programática (como en el observador del filtro observeUserPreferences() )
        binding.cbCellars.setOnClickListener {
            if (binding.cbCellars.isChecked) {
                locationsListVM.filter[0] = true
                locationsListVM.query.value = locationsListVM.query.value
                preferencesVM.updateFilterPreferences(Filter.BY_CELLARS, true)
            }
            else {
                locationsListVM.filter[0] = false
                locationsListVM.query.value = locationsListVM.query.value
                preferencesVM.updateFilterPreferences(Filter.BY_CELLARS, false)
            }
        }
        binding.cbCidermills.setOnClickListener {
            if (binding.cbCidermills.isChecked) {
                locationsListVM.filter[1] = true
                locationsListVM.query.value = locationsListVM.query.value
                preferencesVM.updateFilterPreferences(Filter.BY_CIDERMILLS, true)
            }
            else {
                locationsListVM.filter[1] = false
                locationsListVM.query.value = locationsListVM.query.value
                preferencesVM.updateFilterPreferences(Filter.BY_CIDERMILLS, false)
            }
        }
        binding.cbDairies.setOnClickListener {
            if (binding.cbDairies.isChecked) {
                locationsListVM.filter[2] = true
                locationsListVM.query.value = locationsListVM.query.value
                preferencesVM.updateFilterPreferences(Filter.BY_DAIRIES, true)
            }
            else {
                locationsListVM.filter[2] = false
                locationsListVM.query.value = locationsListVM.query.value
                preferencesVM.updateFilterPreferences(Filter.BY_DAIRIES, false)
            }
        }

    }
}