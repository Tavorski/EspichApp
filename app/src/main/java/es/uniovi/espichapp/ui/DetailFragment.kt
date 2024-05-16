package es.uniovi.espichapp.ui

import android.graphics.text.LineBreaker
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import androidx.fragment.app.viewModels
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
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.uniovi.arqui.adapters.SlideAdapter
import es.uniovi.arqui.util.Utils
import es.uniovi.espichapp.EspichApp
import es.uniovi.espichapp.R
import es.uniovi.espichapp.data.Coordinates
import es.uniovi.espichapp.databinding.FragmentDetailBinding
import es.uniovi.espichapp.domain.DetailViewModel
import es.uniovi.espichapp.domain.PreferencesViewModel
import es.uniovi.espichapp.interfaces.NetworkUseController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFragment : Fragment(),
    NetworkUseController {

    // CAMPOS
    val args: DetailFragmentArgs by navArgs()
    lateinit var locationName: String
    lateinit var coordinates: Coordinates
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailVM: DetailViewModel by viewModels() {
        Utils.ViewModelFactory((activity?.application as EspichApp).repository)
    }
    private val preferencesVM: PreferencesViewModel by viewModels {
        Utils.ViewModelFactory((activity?.application as EspichApp).repository)
    }
    lateinit var rvSlide: RecyclerView
    lateinit var adapterSlide: SlideAdapter

    // OVERRIDES
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            locationName = args.locationName
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailVM.setLocation(locationName)

        // Hacemos que se observe el livedata del viewmodel que contiene el establecimiento a mostrar
        observeLocation()
        
        // Modificamos la toolbar según las necesidades de esta vista
        initToolbar()
    }

    fun observeLocation() {
        detailVM.location.observe(viewLifecycleOwner) { location ->
            with(binding) {
                // Montamos toda la vista
                tvDetailName.text = location.Nombre
                tvDetailCouncil.text = location.Concejo
                tvDetailAddress.text = location.Direccion
                tvDetailDescp.text = buildString {
                    append(location.BreveDescripcion)
                    append("\n\n")
                    append(location.Descripcion)
                }
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    tvDetailDescp.justificationMode =
                        android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
                }

                // Es necesario envolver el parseo de las coordenadas en un trycatch
                try {
                    // Procesamos el argumento string con las coordenadas
                    val tokens: List<String> = location.Coordenadas!!.split(",")
                    coordinates = Coordinates(tokens[0].toDouble(), tokens[1].toDouble())
                } catch (_: Exception) {
                    // Se puede dar una excepcion cuando el contenido de las coordenadas
                    // retornado por Rest es nulo o no es parseable a Double
                    // Si se da el caso, no mostramos el botón
                    linkCoordinates.isVisible = false
                }

                // Añadimos el manejador que cambia la vista al MapFragment
                linkCoordinates.setOnClickListener {
                    findNavController().navigate(
                        es.uniovi.espichapp.ui.DetailFragmentDirections.actionDetailFragmentToMapFragment(
                            coordinates
                        )
                    )
                }

                linkEmail.text = location.Email
                linkPhone.text = location.Telefono
                linkWeb.text = location.Web
                linkFacebook.text = location.Facebook
                linkInstagram.text = location.Instagram

                iconEmail.isEnabled = !location.Email.isNullOrBlank()
                iconPhone.isEnabled = !location.Telefono.isNullOrBlank()
                iconWeb.isEnabled = !location.Web.isNullOrBlank()
                iconFacebook.isEnabled = !location.Facebook.isNullOrBlank()
                iconInstagram.isEnabled = !location.Instagram.isNullOrBlank()

                rvSlide = rvDetailSlide

                // Estos son las url de los slides y sus respetivos titulos, ambos tokenizados
                // para mandarselos al holder y que pinte el reclycerview con Picasso
                val slides: List<String>? = location.Slide?.split(",")
                val titles: List<String>? = location.SlideTitulo?.split(",")

                // Asignamos un layout lineal horizontal
                android.util.Log.d("DEBUG - DF", "Asignado LLM")
                rvSlide.layoutManager = LinearLayoutManager(
                    context,
                    androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
                    false
                );

                // Asignamos un adapter y cargamos las imagenes del establecimiento
                android.util.Log.d("DEBUG - DF", "Instanciado SlideAdapter")
                adapterSlide = SlideAdapter(slides, titles, this@DetailFragment)
                rvSlide.adapter = adapterSlide
            }
        }
    }

    private fun initToolbar() {
        val mainActivity: MainActivity = requireActivity() as MainActivity
        mainActivity.setSupportActionBar(binding.toolbar)

        val supportActionBar = (requireActivity() as MainActivity).supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // activa la flecha de retorno
        supportActionBar?.title = getString(R.string.actionbar_title_detail) // cambiamos el titulo
        supportActionBar?.setHomeButtonEnabled(true)

        mainActivity.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                //menuInflater.inflate(R.menu.menu_main, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                Log.d("Debug - DF", "Se ha pulsado ${menuItem.javaClass},${menuItem.itemId}")

                // Solo hay un botón en este menú, no es necesario identificar el menuitem
                findNavController().popBackStack()

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun areDownloadsAllowed(): Boolean = preferencesVM.areDownloadsAllowed(requireContext())
}