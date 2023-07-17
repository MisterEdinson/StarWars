package com.example.starwars.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.starwars.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details.*

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
        Log.e("-----", url.toString())
        viewModel.getFromName(url.toString())
        viewModel.getDetailsInfo.observe(viewLifecycleOwner) {
            tvDetailsTitle.text = it.name
            when(it.gender){
                null -> {
                    if (it.diameter != null){
                        imgDetailsLogo.setImageResource(R.drawable.logo_planet)
                    }else{
                        imgDetailsLogo.setImageResource(R.drawable.logo_starships)
                    }
                }
                "n/a" -> {imgDetailsLogo.setImageResource(R.drawable.logo_robots)}
                else -> {imgDetailsLogo.setImageResource(R.drawable.logo_people)}
            }
//            adapter?.list?.submitList(it)
        }
    }

    private fun initAdapter(){
        adapter = DetailsFilmsAdapter()
        rvDetailsFilms.adapter = adapter
    }
}