package com.example.starwars.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.data.network.models.searchPeople.ResultsItem
import kotlinx.android.synthetic.main.item_search.view.*

class HomeSearchAdapter : RecyclerView.Adapter<HomeSearchAdapter.HolderSearch>() {

    class HolderSearch(view: View) : RecyclerView.ViewHolder(view)

    val callback = object : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
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
        }
    }

    override fun getItemCount(): Int {
        return list.currentList.size
    }
}