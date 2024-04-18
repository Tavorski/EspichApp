package es.uniovi.espichapp.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import es.uniovi.arqui.util.Utils
import es.uniovi.espichapp.EspichApp
import es.uniovi.espichapp.R
import es.uniovi.espichapp.databinding.FragmentDetailBinding
import es.uniovi.espichapp.databinding.FragmentLocationsListBinding
import es.uniovi.espichapp.domain.DetailViewModel
import es.uniovi.espichapp.model.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    // CAMPOS
    val args: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailVM: DetailViewModel by viewModels() {
        Utils.DetailViewModelFactory((activity?.application as EspichApp).repository)
    }

    // OVERRIDES
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val location: Location = detailVM.getLocation()

        with(binding) {
            tvDetailName.text = location.Nombre
            tvDetailCouncil.text = location.Concejo
            tvDetailAddress.text = location.Direccion
            tvDetailDescp.text = location.Descripcion
            linkCoordinates.text = location.Coordenadas
            linkEmail.text = location.Email
            linkPhone.text = location.Telefono
            linkWeb.text = location.Web
            linkFacebook.text = location.Facebook
            linkInstagram.text = location.Instagram


            iconEmail.setEnabled(!location.Email.isNullOrBlank())
            iconPhone.setEnabled(!location.Telefono.isNullOrBlank())
            iconWeb.setEnabled(!location.Web.isNullOrBlank())
            iconFacebook.setEnabled(!location.Facebook.isNullOrBlank())
            iconInstagram.setEnabled(!location.Instagram.isNullOrBlank())

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        CoroutineScope(Dispatchers.IO).launch {
            detailVM.setLocation(args.locationName)
        }

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}