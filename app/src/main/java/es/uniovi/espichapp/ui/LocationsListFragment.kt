package es.uniovi.espichapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import es.uniovi.arqui.adapters.LocationListAdapter
import es.uniovi.arqui.domain.LocationListViewModel
import es.uniovi.arqui.util.Utils
import es.uniovi.espichapp.EspichApp
import es.uniovi.espichapp.databinding.FragmentLocationsListBinding


class LocationsListFragment : Fragment() {

    private var _binding: FragmentLocationsListBinding? = null
    private val binding get() = _binding!!

    private val locationVM: LocationListViewModel by viewModels {
        Utils.LocationViewModelFactory((activity?.application as EspichApp).repository)
    }
    private val adapter: LocationListAdapter = LocationListAdapter()

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
            locList.let { adapter.submitList(locList) }
        }


        // Asignamos un LayoutManager al recyclerView
        binding.rvLocationList.layoutManager = LinearLayoutManager(this.context)
        // Inicializacion del combo adapter-recyclerview
        binding.rvLocationList.adapter = adapter
        // El recycler tiene tamaño fijo, luego activamos esta propiedad
        binding.rvLocationList.setHasFixedSize(true)

        binding.btActualizar.setOnClickListener {
            locationVM.getLocationsList()
        }
        locationVM.locationsUIStateObservable.observe(viewLifecycleOwner) { result ->
            when (result) {
                is LocationsUIState.Success -> {
                    adapter.submitList(result.datos)
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
}