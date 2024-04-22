package es.uniovi.espichapp.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
import es.uniovi.espichapp.model.Location


class LocationsListFragment :
    Fragment(),
    LocationListEvent,
    SharedPreferences.OnSharedPreferenceChangeListener,
    SwipeRefreshLayout.OnRefreshListener {

    // CAMPOS

    // Binding
    private var _binding: FragmentLocationsListBinding? = null
    private val binding get() = _binding!!

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
        locationsListVM.locationsFromDB.observe(viewLifecycleOwner) { locList ->
            Log.d("DEBUG - LLF", "Se han observado cambios en locationsFromDB")
            locList.let { adapterList.submitList(locList) }

            // hacemos que se quite la animacion circular de carga
            swipeRefreshLayout.isRefreshing = false

            // No necesitamos que se observe esta lista más, realmente solo interesa observar
            // el observable con estados
            locationsListVM.locationsFromDB.removeObservers(viewLifecycleOwner)
        }

        // Una vez inicializada por primera vez la lista de establecimientos, será este observable
        // el que indique la llegada de nuevos datos
        locationsListVM.locationsUIStateObservable.observe(viewLifecycleOwner) { result ->
            when (result) {
                is LocationsUIState.Success -> {

                    adapterList.submitList(result.datos.items)
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
        val locationName: String = locationsListVM.locationsFromDB.value?.get(position)?.Nombre ?: ""
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
            "query" -> {
                if (sharedPreferences != null) {
                    locationsListVM.query = sharedPreferences.getString(key, "").toString()
                }
            }
        }
        locationsListVM.search()
    }





}