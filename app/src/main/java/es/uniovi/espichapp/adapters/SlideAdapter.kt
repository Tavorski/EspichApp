package es.uniovi.arqui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.uniovi.arqui.util.Utils
import es.uniovi.espichapp.databinding.FragmentDetailBinding
import es.uniovi.espichapp.databinding.SlideViewBinding
import es.uniovi.espichapp.interfaces.NetworkUseController
import es.uniovi.espichapp.model.Location

/**
 * Este Adapter y su respectivo ViewHolder se utilizan para mostrar las imagenes de un establecimiento
 * en la pantalla del detalle
 */
class SlideAdapter(private val slides: List<String>?,
                   private val titles: List<String>?,
                   private val netController: NetworkUseController
) : RecyclerView.Adapter<SlideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        Log.d("DEBUG - SA", "onCreateVH")
        val slideViewBinding = SlideViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SlideViewHolder(slideViewBinding, netController)
    }

    override fun getItemCount(): Int = slides?.size ?: 0

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        Log.d("DEBUG - SA", "onBindVH")
        holder.bind(slides?.get(position) ?: "", titles?.get(position) ?: "") // aqui se pasa al holder el slide
    }
}