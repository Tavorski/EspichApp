package es.uniovi.arqui.adapters

import android.graphics.text.LineBreaker
import android.os.Build
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.uniovi.espichapp.R
import es.uniovi.espichapp.databinding.ItemViewBinding
import es.uniovi.espichapp.interfaces.NetworkUseController
import es.uniovi.espichapp.interfaces.OnListItemClickListener
import es.uniovi.espichapp.model.Location

class LocationListViewHolder(
    private val itemViewBinding: ItemViewBinding,
    private val clickListener: OnListItemClickListener,
    private val netController: NetworkUseController
) : RecyclerView.ViewHolder(itemViewBinding.root), View.OnClickListener{

    init {
        itemViewBinding.root.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            clickListener.onItemClick(position)
        }
    }

    fun bind(item: Location) {
        with(itemViewBinding) {
            tvName.text = item.Nombre
            tvCouncil.text = item.Concejo
            tvShortDescp.text = item.BreveDescripcion
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                tvShortDescp.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
            }

            /*
            Las categorías "Bodega", "Llagar" y "Quesería" no son excluyentes, se considera que podría
            ocurrir que un establecimiento produzca o venda más de un tipo de producto
             */
            /*
            Estos lets se hacen para que cambien los margenes derechos de los iconos en funcion de cuales
            son visibles
             */
            item.isQueseria().let {
                iconCheese.isVisible = it
                val p: MarginLayoutParams = iconCider.layoutParams as MarginLayoutParams
                if(!it) {
                    p.setMargins(0,0,32,0)
                    iconCider.layoutParams = p
                }
                else {
                    p.setMargins(0,0,0,0)
                    iconCider.layoutParams = p
                }
            }
            item.isLlagar().let {
                iconCider.isVisible = it
                val p: MarginLayoutParams = iconCask.layoutParams as MarginLayoutParams
                if(!it) {
                    p.setMargins(0,0,32,0)
                    iconCask.layoutParams = p
                }
                else {
                    p.setMargins(0,0,0,0)
                    iconCask.layoutParams = p
                }
            }
            iconCask.isVisible = item.isBodega()
            // Descargamos con Picasso el slide
            if (netController.areDownloadsAllowed()) {
                Picasso
                    .get()
                    .load("https://www.turismoasturias.es/${item.Slide}")
                    .centerCrop().resize(150,150)
                    .placeholder(R.drawable.image_placeholder2)
                    .into(imageLocation)
            }
            else {
                Picasso
                    .get()
                    .load(R.drawable.image_placeholder_nowifi)
                    .centerInside().resize(200,200)
                    .placeholder(R.drawable.image_placeholder2)
                    .into(imageLocation)
            }

            // Pintamos los tablones
            /*Picasso
                .get()
                .load(R.drawable.bg_itemview_woodplank)
                .placeholder(R.drawable.bg_itemview_woodplank)
                .into(imageBackground)*/
        }
    }
}


