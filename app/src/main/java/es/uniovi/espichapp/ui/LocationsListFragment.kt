package es.uniovi.espichapp.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import es.uniovi.arqui.adapters.LocationListAdapter
import es.uniovi.arqui.domain.LocationListViewModel
import es.uniovi.arqui.util.Utils
import es.uniovi.espichapp.EspichApp
import es.uniovi.espichapp.databinding.FragmentLocationsListBinding
import es.uniovi.espichapp.model.Location


class LocationsListFragment : Fragment(), LocationListAdapter.RecyclerViewEvent, SwipeRefreshLayout.OnRefreshListener {

    // CAMPOS
    private var _binding: FragmentLocationsListBinding? = null
    private val binding get() = _binding!!

    private val locationVM: LocationListViewModel by viewModels {
        Utils.LocationViewModelFactory((activity?.application as EspichApp).repository)
    }
    lateinit var recyclerView: RecyclerView
    private val adapterList: LocationListAdapter = LocationListAdapter(this)

    // para actualizar al scrollear hacia arriba
    lateinit var swipeRefreshLayout: SwipeRefreshLayout


    // OVERRIDES
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLocationsListBinding.inflate(inflater, container, false)
        Log.d("DEBUG","Se ha creado el binding del listfragment")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hacemos que se observe la lista y que se le pasen datos al adapter
        // cuando haya cambios
        locationVM.locationList.observe(viewLifecycleOwner) { locList ->
            locList.let { adapterList.submitList(locList) }
        }

        // Asignamos un LayoutManager al recyclerView
        recyclerView = binding.rvLocationList
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        // Inicializacion del combo adapter-recyclerview
        recyclerView.adapter = adapterList
        // El recycler tiene tamaño fijo, luego activamos esta propiedad
        recyclerView.setHasFixedSize(true)
        swipeRefreshLayout = binding.srLayout
        swipeRefreshLayout.setOnRefreshListener(this)

        /*binding.btActualizar.setOnClickListener {
            locationVM.getLocationsList()
        }*/
        locationVM.locationsUIStateObservable.observe(viewLifecycleOwner) { result ->
            when (result) {
                is LocationsUIState.Success -> {
                    adapterList.submitList(result.datos)
                    Log.d("DEBUG - LLFragment","Acceso realizado con exito")
                    Snackbar.make(view, "Se han observado cambios", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                }
                is LocationsUIState.Error -> {
                    Snackbar.make(view, result.message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        Log.d("DEBUG-ViewHolder", "Se va a intentar navegar al fragmento")
        val locationName: String = locationVM.locationList.value?.get(position)?.Nombre ?: ""
        if (locationName == "") return
        findNavController().navigate(
             LocationsListFragmentDirections.actionLocationsListFragmentToDetailFragment(locationName)
        )

    }

    override fun onRefresh() {
        locationVM.getLocationsList()

        // hacemos que se quite la animacion circular de carga
        swipeRefreshLayout.isRefreshing = false
    }
}