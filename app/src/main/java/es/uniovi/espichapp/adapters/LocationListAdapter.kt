package es.uniovi.arqui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import es.uniovi.arqui.util.Utils
import es.uniovi.espichapp.databinding.ItemViewBinding
import es.uniovi.espichapp.model.Location

class LocationListAdapter(
    val listener: RecyclerViewEvent
) : ListAdapter<Location, LocationViewHolder>(DIFF_CALLBACK_LIST) {


    interface RecyclerViewEvent {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val itemViewBinding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(itemViewBinding, listener)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position)) // aqui se pasa al holder el establecimiento
    }

    companion object DIFF_CALLBACK_LIST: DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.Nombre == newItem.Nombre
        }
        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }
    }

}