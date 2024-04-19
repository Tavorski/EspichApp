package es.uniovi.arqui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.uniovi.arqui.util.Utils
import es.uniovi.espichapp.databinding.FragmentDetailBinding
import es.uniovi.espichapp.databinding.SlideViewBinding
import es.uniovi.espichapp.model.Location

class SlideAdapter(val slides: List<String>?) : RecyclerView.Adapter<SlideViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {

        Log.d("DEBUG - SA", "onCreateVH")
        val slideViewBinding = SlideViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SlideViewHolder(slideViewBinding)
    }

    override fun getItemCount(): Int = slides?.size ?: 0


    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        Log.d("DEBUG - SA", "onBindVH")
        holder.bind(slides?.get(position) ?: "") // aqui se pasa al holder el slide
    }

    companion object DIFF_CALLBACK_SLIDE: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}