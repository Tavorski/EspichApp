package es.uniovi.arqui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.uniovi.arqui.util.Utils
import es.uniovi.espichapp.databinding.ItemViewBinding
import es.uniovi.espichapp.model.Location

class LocationListAdapter(
    val listener: RecyclerViewEvent
) : ListAdapter<Location, LocationViewHolder>(Utils.DIFF_CALLBACK) {


    interface RecyclerViewEvent {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val itemViewBinding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(itemViewBinding, listener)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}