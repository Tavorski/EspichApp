package es.uniovi.arqui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import es.uniovi.espichapp.databinding.ItemViewBinding
import es.uniovi.espichapp.interfaces.NetworkUseController
import es.uniovi.espichapp.interfaces.OnListItemClickListener
import es.uniovi.espichapp.model.Location

class LocationListAdapter(
    private val clickListener: OnListItemClickListener,
    private val netController: NetworkUseController
) : ListAdapter<Location, LocationListViewHolder>(DIFF_CALLBACK_LIST) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationListViewHolder {
        val itemViewBinding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationListViewHolder(itemViewBinding, clickListener, netController)
    }

    override fun onBindViewHolder(holder: LocationListViewHolder, position: Int) {
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