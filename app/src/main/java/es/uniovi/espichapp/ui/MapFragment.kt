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
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import es.uniovi.espichapp.R
import es.uniovi.espichapp.databinding.FragmentMapBinding
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


private const val TAG = "Debug - MapFrag"

class MapFragment : Fragment() {

    // CAMPOS
    val args: MapFragmentArgs by navArgs()      // Argumentos (SafeArgs)

    // Binding
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    lateinit var map: MapView                   // Mapa
    lateinit var mapController: IMapController  // Controlador
    lateinit var marker: Marker                 // Marcador
    lateinit var locationPoint: GeoPoint        // Coordenadas


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configuración previa de Osmdroid
        Configuration.getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Instanciar mapa y controlador
        map = binding.mapView
        map.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)
        mapController = map.controller

        // Tomamos los argumentos de SafeArgs
        val latitude: Double = args.coordinates.latitude
        val longitude: Double = args.coordinates.longitude

        // Nos movemos a las coordenadas del establecimiento
        locationPoint = GeoPoint(latitude, longitude)
        mapController.zoomTo(16,2)
        mapController.animateTo(locationPoint)

        // Preparacion del marcador del establecimiento
        marker = Marker(map)
        marker.position = locationPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = args.coordinates.locationName +
                "\n" +
                args.coordinates.address

        // Pintamos el marcador
        map.overlays.add(marker)

        // Modificamos la toolbar según las necesidades de esta vista
        initToolbar()
    }

    /**
     * Inicializa una toolbar muy sencilla con una flecha de retorno a la lista de establecimientos
     */
    private fun initToolbar() {
        val mainActivity: MainActivity = requireActivity() as MainActivity
        mainActivity.setSupportActionBar(binding.toolbar)

        val supportActionBar = (requireActivity() as MainActivity).supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // activa la flecha de retorno
        supportActionBar?.title = getString(R.string.actionbar_title_map) // cambiamos el titulo
        supportActionBar?.setHomeButtonEnabled(true)

        mainActivity.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_detail, menu)

            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                Log.d(TAG, "Se ha pulsado ${menuItem.javaClass},${menuItem.itemId}," +
                        "${menuItem}")

                // Solo hay un botón en este menú, no es necesario identificar el menuitem
                findNavController().popBackStack()

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}