package com.example.starwars.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.data.local.models.FilmsEntity
import kotlinx.android.synthetic.main.item_films.view.*

class DetailsFilmsAdapter : RecyclerView.Adapter<DetailsFilmsAdapter.FilmsHolder>() {

    class FilmsHolder(view: View) : RecyclerView.ViewHolder(view)

    val callback = object : DiffUtil.ItemCallback<FilmsEntity>() {
        override fun areItemsTheSame(oldItem: FilmsEntity, newItem: FilmsEntity): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: FilmsEntity, newItem: FilmsEntity): Boolean {
            return oldItem.url == newItem.url
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
            when (item.url) {
                "https://swapi.dev/api/films/1/" -> {
                    imgDetailsFilmLogo.setImageResource(R.drawable.episode_1)
                }
                "https://swapi.dev/api/films/2/" -> {
                    imgDetailsFilmLogo.setImageResource(R.drawable.episode_2)
                }
                "https://swapi.dev/api/films/3/" -> {
                    imgDetailsFilmLogo.setImageResource(R.drawable.episode_3)
                }
                "https://swapi.dev/api/films/4/" -> {
                    imgDetailsFilmLogo.setImageResource(R.drawable.episode_4)
                }
                "https://swapi.dev/api/films/5/" -> {
                    imgDetailsFilmLogo.setImageResource(R.drawable.episode_5)
                }
                "https://swapi.dev/api/films/6/" -> {
                    imgDetailsFilmLogo.setImageResource(R.drawable.episode_6)
                }
                "https://swapi.dev/api/films/7/" -> {
                    imgDetailsFilmLogo.setImageResource(R.drawable.episode_7)
                }
                "https://swapi.dev/api/films/8/" -> {
                    imgDetailsFilmLogo.setImageResource(R.drawable.episode_8)
                }
                "https://swapi.dev/api/films/9/" -> {
                    imgDetailsFilmLogo.setImageResource(R.drawable.episode_9)
                }
            }
            tvDetailsFilmTitle.text = item.title
            tvDetailsFilmDirector.text = item.director
            tvDetailsFilmProducer.text = item.producer
        }
    }

    override fun getItemCount(): Int {
        return list.currentList.size
    }
}