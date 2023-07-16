package com.example.starwars.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.data.local.models.FavoriteEntity
import kotlinx.android.synthetic.main.item_search.view.*

class HomeSearchAdapter(
    val clickFavorite: (FavoriteEntity) -> Unit
) : RecyclerView.Adapter<HomeSearchAdapter.HolderSearch>() {

    class HolderSearch(view: View) : RecyclerView.ViewHolder(view)

    val callback = object : DiffUtil.ItemCallback<FavoriteEntity>() {
        override fun areItemsTheSame(
            oldItem: FavoriteEntity,
            newItem: FavoriteEntity
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: FavoriteEntity,
            newItem: FavoriteEntity
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }

    var list = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderSearch {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return HolderSearch(view)
    }

    override fun onBindViewHolder(holder: HolderSearch, position: Int) {
        val item = list.currentList[position]
        holder.itemView.apply {
            tvNameItem.text = item.name
            tvGenderItem.text = item.gender

            imgFavoriteItem.setOnClickListener {
                clickFavorite(item)
                Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.currentList.size
    }
}