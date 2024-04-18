package es.uniovi.arqui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import es.uniovi.arqui.util.Utils
import es.uniovi.espichapp.databinding.FragmentDetailBinding
import es.uniovi.espichapp.databinding.ItemViewBinding
import es.uniovi.espichapp.model.Location
import es.uniovi.espichapp.ui.LocationsListFragmentDirections

class DetailAdapter() : ListAdapter<Location, DetailViewHolder>(Utils.DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val detailBinding = FragmentDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(detailBinding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val item: Location = getItem(position)
        holder.bind(item)

    }
}