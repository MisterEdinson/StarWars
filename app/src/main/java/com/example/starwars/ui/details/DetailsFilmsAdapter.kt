package com.example.starwars.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.data.network.models.Films

class DetailsFilmsAdapter : RecyclerView.Adapter<DetailsFilmsAdapter.FilmsHolder>() {
    class FilmsHolder(view: View) : RecyclerView.ViewHolder(view)

    val callback = object : DiffUtil.ItemCallback<Films>(){
        override fun areItemsTheSame(oldItem: Films, newItem: Films): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Films, newItem: Films): Boolean {
            return oldItem.title == newItem.title
        }
    }

    val list = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_films, parent, false)
        return FilmsHolder(view)
    }

    override fun onBindViewHolder(holder: FilmsHolder, position: Int) {
        val item = list.currentList[position]
        holder.itemView.apply {

        }
    }

    override fun getItemCount(): Int {
        return list.currentList.size
    }
}