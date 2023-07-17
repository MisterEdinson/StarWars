package com.example.starwars.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.data.local.models.FavoriteEntity
import kotlinx.android.synthetic.main.item_search.view.*

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>(){
    class FavoriteHolder(view: View):RecyclerView.ViewHolder(view)

    val callback = object : DiffUtil.ItemCallback<FavoriteEntity>() {
        override fun areItemsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
            return oldItem.url == newItem.url
        }
    }

    val list = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return FavoriteHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        val item = list.currentList[position]
        holder.itemView.apply {
            holder.itemView.apply {
                tvNameItem.text = item.name
                if(item.gender != null){
                    tvGenderItem.text = item.gender
                    tvStarships.text = item.starships
                }
                if(item.model != null){
                    tvModel.text = item.model
//                tvManufacture.text = item.manufacturer?.take(7)
                    tvPassengers.text = item.passengers
                }
                if(item.diameter != null){
                    tvDiameter.text = item.diameter
                    tvPopulation.text = item.population
                }

//                imgFavoriteItem.setOnClickListener {
//                    clickFavorite(item)
//                    Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
//                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.currentList.size
    }
}