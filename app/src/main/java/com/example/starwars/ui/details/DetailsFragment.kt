package com.example.starwars.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.starwars.R
import com.example.starwars.data.local.models.FilmsEntity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details.*
import okhttp3.HttpUrl.Companion.parse
import org.json.JSONArray
import java.util.logging.Level.parse

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel by viewModels<DetailsViewModel>()
    private var adapter:DetailsFilmsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        val url = arguments?.getString("url")
        viewModel.getFromName(url.toString())
        viewModel.getDetailsInfo.observe(viewLifecycleOwner) {
            val gson = Gson()
            tvDetailsTitle.visibility = View.VISIBLE
            tvDetailsTitle.text = it.name
            when(it.gender){
                null -> {
                    if (it.diameter != null){
                        tvDetailsDiameter.visibility = View.VISIBLE
                        tvDetailsPopulation.visibility = View.VISIBLE
                        tvDetailsDiameter.text = "Diameter : ${it.diameter}"
                        tvDetailsPopulation.text = "Population : ${it.population}"
                        imgDetailsLogo.setImageResource(R.drawable.logo_planet)
                    }else{
                        tvDetailsModel.visibility = View.VISIBLE
                        tvDetailsManufacture.visibility = View.VISIBLE
                        tvDetailsPassengers.visibility = View.VISIBLE
                        tvDetailsModel.text = "Model : ${it.model}"
                        tvDetailsManufacture.text = it.manufacturer
                        tvDetailsPassengers.text = "Passengers : ${it.passengers}"
                        imgDetailsLogo.setImageResource(R.drawable.logo_starships)
                    }
                }
                "n/a" -> {
                    tvDetailsGender.visibility = View.VISIBLE
                    tvDetailsStarships.visibility = View.VISIBLE
                    tvDetailsGender.text = "Gender : ${it.gender}"
                    tvDetailsStarships.text = "Summ starships : ${it.starships}"
                    imgDetailsLogo.setImageResource(R.drawable.logo_robots)
                }
                else -> {
                    tvDetailsGender.visibility = View.VISIBLE
                    tvDetailsStarships.visibility = View.VISIBLE
                    tvDetailsGender.text = "Gender : ${it.gender}"
                    tvDetailsStarships.text = "Starships : ${it.starships}"
                    imgDetailsLogo.setImageResource(R.drawable.logo_people)
                }
            }
            val filmsArr : Array<String> = gson.fromJson(it.films, Array<String>::class.java)
            getFilm(filmsArr)
        }

        viewModel.getFilmArr.observe(viewLifecycleOwner){
            adapter?.list?.submitList(it)
        }
    }

    private fun initAdapter(){
        adapter = DetailsFilmsAdapter ()
        rvDetailsFilms.adapter = adapter
    }

    private fun getFilm(url:Array<String>){
        viewModel.getFilm(url)
    }
}


