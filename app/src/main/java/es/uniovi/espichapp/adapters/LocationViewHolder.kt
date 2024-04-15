package es.uniovi.arqui.adapters

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import es.uniovi.espichapp.databinding.ItemViewBinding
import es.uniovi.espichapp.model.Location

class LocationViewHolder(val listItemBinding: ItemViewBinding) : RecyclerView.ViewHolder(listItemBinding.root) {

    fun bind(item: Location) {
        with(listItemBinding) {
            tvName.text = item.name
            tvCouncil.text = item.council

            /*
            Las categorías "Bodega", "Llagar" y "Quesería" no son excluyentes, se considera que podría
            ocurrir que un establecimiento produzca o venda más de un tipo de producto
             */
            iconCask.isVisible = isBodega(item)
            iconCider.isVisible = isLlagar(item)
            iconCheese.isVisible = isQueseria(item)

            TODO("Hay que conectar aquí con la página web de turismo del principado para obtener el slideshow")
        }
    }

    /*
    isBodega() hace un barrido rápido de los datos del establecimiento para determinar si puede ser
    un lugar dedicado a la elaboración de vinos
     */
    fun isBodega(item: Location): Boolean {
        var ret: Boolean
        with(item.name) {
            ret = contains("bodega",true)
                    || contains("vino",true)
        }
        with(item.shortDescription) {
            ret = ret || this?.contains("bodega",true) ?: false
                    || this?.contains("vino",true) ?: false
        }
        with(item.description) {
            ret = ret || this?.contains("bodega",true) ?: false
                    || this?.contains("vino",true) ?: false
        }
        with(item.products) {
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
        with(item.name) {
            ret = contains("sidra",true)
                    || contains("llagar",true)
                    || contains("pomarada",true)
        }
        with(item.shortDescription) {
            ret = ret || this?.contains("sidra",true) ?: false
                    || this?.contains("llagar",true) ?: false
                    || this?.contains("pomarada",true) ?: false
        }
        with(item.description) {
            ret = ret || this?.contains("sidra",true) ?: false
                    || this?.contains("llagar",true) ?: false
                    || this?.contains("pomarada",true) ?: false
        }
        with(item.products) {
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
        with(item.name) {
            ret = contains("queso",true)
                    || contains("quesería",true)
        }
        with(item.shortDescription) {
            ret = ret || this?.contains("queso",true) ?: false
                    || this?.contains("quesería",true) ?: false
        }
        with(item.description) {
            ret = ret || this?.contains("queso",true) ?: false
                    || this?.contains("quesería",true) ?: false
        }
        with(item.products) {
            ret = ret || this?.contains("queso",true) ?: false
        }
        return ret;
    }
}