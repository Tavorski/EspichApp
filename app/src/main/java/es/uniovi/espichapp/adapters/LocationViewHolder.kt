package es.uniovi.arqui.adapters

import android.graphics.BitmapFactory
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import es.uniovi.espichapp.databinding.ItemViewBinding
import es.uniovi.espichapp.model.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL
import java.net.URLStreamHandler

class LocationViewHolder(val listItemBinding: ItemViewBinding) : RecyclerView.ViewHolder(listItemBinding.root) {

    fun bind(item: Location) {
        with(listItemBinding) {
            tvName.text = item.Nombre
            tvCouncil.text = item.Concejo

            /*
            Las categorías "Bodega", "Llagar" y "Quesería" no son excluyentes, se considera que podría
            ocurrir que un establecimiento produzca o venda más de un tipo de producto
             */
            iconCask.isVisible = isBodega(item)
            iconCider.isVisible = isLlagar(item)
            iconCheese.isVisible = isQueseria(item)

            setSlideFromUrl(item.Slide)

        }
    }
    fun setSlideFromUrl(slidePath: String?) {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val inputStream: InputStream = URL("https://www.turismoasturias.es/$slidePath").openStream()
                listItemBinding.imageLocation.setImageBitmap(BitmapFactory.decodeStream(inputStream))
            }
            catch (_: Exception) {

            }
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