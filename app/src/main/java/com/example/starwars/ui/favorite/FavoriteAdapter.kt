package com.example.starwars.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.data.local.models.FavoriteEntity
import kotlinx.android.synthetic.main.item_people.view.*
import kotlinx.android.synthetic.main.item_planet.view.*
import kotlinx.android.synthetic.main.item_starships.view.*

class FavoriteAdapter(val delFavorite: (String) -> Unit) : RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>() {
    class FavoriteHolder(view: View) : RecyclerView.ViewHolder(view)

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
        when (viewType) {
            0 -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_people, parent, false)
                return FavoriteHolder(view)
            }
            1 -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_planet, parent, false)
                return FavoriteHolder(view)
            }
            else -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_starships, parent, false)
                return FavoriteHolder(view)
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
        } else {
            //starships
            return 2
        }
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        val item = list.currentList[position]
        holder.itemView.apply {
            if (item.gender != null) {
                tvNamePeopleItem.text = item.name
                tvGenderPeopleItem.text = "Gender : ${item.gender}"
                tvStarshipsPeopleItem.text = "Starships : ${item.starships}"
                imgFavoriteAddPeopleItem.setImageResource(R.drawable.ic_star)

                imgFavoriteAddPeopleItem.setOnClickListener{
                    Toast.makeText(context, "delete ${item.name}", Toast.LENGTH_SHORT).show()
                    delFavorite(item.url.toString())
                }
                cardFavoritePeople.setOnClickListener {
                    val bundle = bundleOf("url" to item.url)
                    findNavController().navigate(
                        R.id.action_favoriteFragment_to_detailsFragment,
                        bundle
                    )
                }

            }
            if (item.diameter != null) {
                tvNamePlanetItem.text = item.name
                tvDiameterPlanetItem.text = "Diameter : ${item.diameter}"
                tvPopulationPlanetItem.text = "Population : ${item.population}"
                imgFavoriteAddPlanetItem.setImageResource(R.drawable.ic_star)

                imgFavoriteAddPlanetItem.setOnClickListener{
                    Toast.makeText(context, "delete ${item.name}", Toast.LENGTH_SHORT).show()
                    delFavorite(item.url.toString())
                }

                cardFavoritePlanet.setOnClickListener {
                    val bundle = bundleOf("url" to item.url)
                    findNavController().navigate(
                        R.id.action_favoriteFragment_to_detailsFragment,
                        bundle
                    )
                }
            }
            if (item.model != null) {
                tvNameStarshipsItem.text = item.name
                tvModelStarshipsItem.text = "Model : ${item.model}"
                tvManufactureStarshipsItem.text = item.manufacturer
                tvPassengersStarshipsItem.text = "Passengers : ${item.passengers}"
                imgFavoriteAddStarshipsItem.setImageResource(R.drawable.ic_star)

                imgFavoriteAddStarshipsItem.setOnClickListener{
                    Toast.makeText(context, "delete ${item.name}", Toast.LENGTH_SHORT).show()
                    delFavorite(item.url.toString())
                }

                cardFavoriteStarships.setOnClickListener {
                    val bundle = bundleOf("url" to item.url)
                    findNavController().navigate(
                        R.id.action_favoriteFragment_to_detailsFragment,
                        bundle
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.currentList.size
    }
}