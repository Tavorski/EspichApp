package es.uniovi.arqui.adapters

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.uniovi.espichapp.R
import es.uniovi.espichapp.databinding.FragmentDetailBinding
import es.uniovi.espichapp.databinding.SlideViewBinding
import es.uniovi.espichapp.interfaces.NetworkUseController
import es.uniovi.espichapp.model.Location

class SlideViewHolder(private val slideViewBinding: SlideViewBinding,
                      private val netController: NetworkUseController
) : RecyclerView.ViewHolder(slideViewBinding.root) {

    // En este bind, se carga una imagen en una de las "diapositivas" de la vista del detalle
    fun bind(slide: String, title: String) {
        with(slideViewBinding) {
            Log.d("DEBUG - SVH", "Nullcheck de '${slide}'")
            if(slide == "") return

            // Descargamos con Picasso el slide
            Log.d("DEBUG - SVH", "Se va a cargar una imagen del slide")
            if (netController.areDownloadsAllowed()) {
                Picasso
                    .get()
                    .load("https://www.turismoasturias.es/${slide}")
                    .into(imageSlide)
            }
            else {
                Picasso
                    .get()
                    .load(R.drawable.image_placeholder_nowifi)
                    .centerInside().resize(200,200)
                    .placeholder(R.drawable.image_placeholder2)
                    .into(imageSlide)
            }
            // Escribimos el titulo de la foto
            tvSlideTitle.text = title
        }
    }
}