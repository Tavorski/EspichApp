package es.uniovi.espichapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import es.uniovi.arqui.adapters.LocationListAdapter
import es.uniovi.arqui.domain.LocationViewModel
import es.uniovi.arqui.util.Utils
import es.uniovi.espichapp.EspichApp
import es.uniovi.espichapp.databinding.FragmentLocationsListBinding


class LocationsListFragment : Fragment() {

    private var _binding: FragmentLocationsListBinding? = null
    private val binding get() = _binding!!

    private val locationVM: LocationViewModel by viewModels {
        Utils.LocationViewModelFactory((activity?.application as EspichApp).repository)
    }
    private val adapter: LocationListAdapter = LocationListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/


        locationVM.locationNames.observe(viewLifecycleOwner) { names ->
            names.let { adapter.submitList(names.toMutableList()) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}