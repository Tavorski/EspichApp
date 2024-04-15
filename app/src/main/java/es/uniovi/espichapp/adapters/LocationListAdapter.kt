package es.uniovi.arqui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import es.uniovi.arqui.util.Utils
import es.uniovi.espichapp.databinding.ItemViewBinding
import es.uniovi.espichapp.model.Location

class LocationListAdapter() : ListAdapter<Location, LocationViewHolder>(Utils.DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val listItemBinding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}