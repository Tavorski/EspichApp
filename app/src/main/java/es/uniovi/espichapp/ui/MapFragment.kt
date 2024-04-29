package es.uniovi.espichapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import es.uniovi.espichapp.R
import es.uniovi.espichapp.databinding.FragmentDetailBinding
import es.uniovi.espichapp.databinding.FragmentMapBinding
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


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

        // Procesamos el argumento string con las coordenadas
        val coordinates: List<String> = args.coordinates.split(",")
        val latitude: Double = coordinates[0].toDouble()
        val longitude: Double = coordinates[1].toDouble()

        Log.d("Debug - MF", "Se va a cargar el mapa en las coordenadas ${latitude},${longitude}")

        // Nos movemos a las coordenadas del establecimiento
        locationPoint = GeoPoint(latitude, longitude)
        mapController.zoomTo(16,2)
        mapController.animateTo(locationPoint)

        // Preparacion del marcador del establecimiento
        marker = Marker(map)
        marker.position = locationPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        // Pintamos el marcador
        map.overlays.add(marker)
    }
}