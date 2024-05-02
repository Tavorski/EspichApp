package es.uniovi.arqui.adapters

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.uniovi.espichapp.R
import es.uniovi.espichapp.databinding.ItemViewBinding
import es.uniovi.espichapp.interfaces.LocationListEvent
import es.uniovi.espichapp.model.Location

class LocationListViewHolder(
    val itemViewBinding: ItemViewBinding,
    val listener: LocationListEvent
) : RecyclerView.ViewHolder(itemViewBinding.root), View.OnClickListener{

    init {
        itemViewBinding.root.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            listener.onItemClick(position)
        }
    }

    fun bind(item: Location) {
        with(itemViewBinding) {
            tvName.text = item.Nombre
            tvCouncil.text = item.Concejo

            /*
            Las categorías "Bodega", "Llagar" y "Quesería" no son excluyentes, se considera que podría
            ocurrir que un establecimiento produzca o venda más de un tipo de producto
             */
            iconCask.isVisible = isBodega(item)
            iconCider.isVisible = isLlagar(item)
            iconCheese.isVisible = isQueseria(item)

            // Descargamos con Picasso el slide
            Picasso
                .get()
                .load("https://www.turismoasturias.es/${item.Slide}")
                .placeholder(R.drawable.image_placeholder2)
                .into(imageLocation)

            // Pintamos los tablones
            Picasso
                .get()
                .load(R.drawable.bg_itemview_woodplank)
                .placeholder(R.drawable.bg_itemview_woodplank)
                .into(imageBackground)
        }
    }

    /*
   isBodega() hace un barrido rápido de los datos del establecimiento para determinar si puede ser
   un lugar dedicado a la elaboración de vinos
    */
    fun isBodega(item: Location): Boolean {
        var ret: Boolean
        with(item.Nombre) {
            ret = contains("bodega",true)
                    || contains("vino",true)
        }
        with(item.BreveDescripcion) {
            ret = ret || this?.contains("bodega",true) ?: false
                    || this?.contains("vino",true) ?: false
        }
        with(item.Descripcion) {
            ret = ret || this?.contains("vino",true) ?: false
        }
        with(item.Productos) {
            ret = ret || this?.contains("vino",true) ?: false
        }
        return ret;
    }

    /*
    isLlagar() hace un barrido rápido de los datos del establecimiento para determinar si puede ser
    un lugar dedicado a la elaboración de sidra
     */
    fun isLlagar(item: Location): Boolean {
        var ret: Boolean
        with(item.Nombre) {
            ret = contains("sidra",true)
                    || contains("llagar",true)
                    || contains("pomarada",true)
        }
        with(item.BreveDescripcion) {
            ret = ret || this?.contains("sidra",true) ?: false
                    || this?.contains("llagar",true) ?: false
                    || this?.contains("pomarada",true) ?: false
        }
        with(item.Descripcion) {
            ret = ret || this?.contains("sidra",true) ?: false
                    || this?.contains("llagar",true) ?: false
                    || this?.contains("pomarada",true) ?: false
        }
        with(item.Productos) {
            ret = ret || this?.contains("sidra",true) ?: false
        }
        return ret;
    }

    /*
    isQueseria() hace un barrido rápido de los datos del establecimiento para determinar si puede ser
    un lugar dedicado a la elaboración de queso
     */
    fun isQueseria(item: Location): Boolean {
        var ret: Boolean
        with(item.Nombre) {
            ret = contains("queso",true)
                    || contains("quesería",true)
        }
        with(item.BreveDescripcion) {
            ret = ret || this?.contains("queso",true) ?: false
                    || this?.contains("quesería",true) ?: false
        }
        with(item.Descripcion) {
            ret = ret || this?.contains("queso",true) ?: false
                    || this?.contains("quesería",true) ?: false
        }
        with(item.Productos) {
            ret = ret || this?.contains("queso",true) ?: false
        }
        return ret;
    }

}


