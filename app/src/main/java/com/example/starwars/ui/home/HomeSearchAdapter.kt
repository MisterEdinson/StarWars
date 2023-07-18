package com.example.starwars.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.data.local.models.FavoriteEntity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_people.view.*
import kotlinx.android.synthetic.main.item_planet.view.*
import kotlinx.android.synthetic.main.item_search.view.*
import kotlinx.android.synthetic.main.item_starships.view.*

class HomeSearchAdapter(
    val clickFavorite: (FavoriteEntity) -> Unit
) : RecyclerView.Adapter<HomeSearchAdapter.HolderSearch>() {

    class HolderSearch(view: View) : RecyclerView.ViewHolder(view)

    val callback = object : DiffUtil.ItemCallback<FavoriteEntity>() {
        override fun areItemsTheSame(
            oldItem: FavoriteEntity,
            newItem: FavoriteEntity
        ): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: FavoriteEntity,
            newItem: FavoriteEntity
        ): Boolean {
            return oldItem.url == newItem.url
        }
    }

    var list = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderSearch {
        when (viewType) {
            0 -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_people, parent, false)
                return HolderSearch(view)
            }
            1 -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_planet, parent, false)
                return HolderSearch(view)
            }
            else -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_starships, parent, false)
                return HolderSearch(view)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (list.currentList[position].gender != null) {
            //people
            return 0
        }
        if (list.currentList[position].diameter != null) {
            //planets
            return 1
        }
        else{
            //starships
            return 2
        }

    }

    override fun onBindViewHolder(holder: HolderSearch, position: Int) {
        val item = list.currentList[position]
        holder.itemView.apply {
            if (item.gender != null) {
                tvNamePeopleItem.text = item.name
                tvGenderPeopleItem.text = "Gender : ${item.gender}"
                tvStarshipsPeopleItem.text = "Starships : ${item.starships}"

                imgFavoriteAddPeopleItem.setOnClickListener{
                    clickFavorite(item)
                    Toast.makeText(context, "add ${item.name}", Toast.LENGTH_SHORT).show()
                }
            }
            if (item.diameter != null) {
                tvNamePlanetItem.text = item.name
                tvDiameterPlanetItem.text = "Diameter : ${item.diameter}"
                tvPopulationPlanetItem.text = "Population : ${item.population}"

                imgFavoriteAddPlanetItem.setOnClickListener{
                    clickFavorite(item)
                    Toast.makeText(context, "add ${item.name}", Toast.LENGTH_SHORT).show()
                }
            }
            if (item.model != null) {
                tvNameStarshipsItem.text = item.name
                tvModelStarshipsItem.text = "Model : ${item.model}"
                tvManufactureStarshipsItem.text = item.manufacturer
                tvPassengersStarshipsItem.text = "Passengers : ${item.passengers}"

                imgFavoriteAddStarshipsItem.setOnClickListener{
                    clickFavorite(item)
                    Toast.makeText(context, "add ${item.name}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.currentList.size
    }
}