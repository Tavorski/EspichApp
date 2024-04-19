package es.uniovi.arqui.adapters

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.uniovi.espichapp.databinding.FragmentDetailBinding
import es.uniovi.espichapp.databinding.SlideViewBinding
import es.uniovi.espichapp.model.Location

class SlideViewHolder(val slideViewBinding: SlideViewBinding) : RecyclerView.ViewHolder(slideViewBinding.root) {

    fun bind(slide: String) {
        with(slideViewBinding) {
            Log.d("DEBUG - SVH", "Nullcheck de '${slide}'")
            if(slide == "") return
            // Descargamos con Picasso el slide
            Log.d("DEBUG - SVH", "Se va a cargar una imagen del slide")
            Picasso
                .get()
                .load("https://www.turismoasturias.es/${slide}")
                .into(imageSlide)
            // Descargamos con Picasso el slide

        }
    }

}