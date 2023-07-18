package com.example.starwars.ui.home

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.starwars.R
import com.example.starwars.data.local.models.FavoriteEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private var adapter: HomeSearchAdapter? = null
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.searchPeopleData.observe(viewLifecycleOwner) {
            adapter?.list?.submitList(it)
            if (it.isEmpty()) {
                pbSearch.visibility = View.INVISIBLE
                tvResultNull.visibility = View.VISIBLE
            } else {
                rvSearchHome.visibility = View.VISIBLE
                pbSearch.visibility = View.INVISIBLE
                tvResultNull.visibility = View.INVISIBLE
            }
        }
        etSearchName.addTextChangedListener {
            search(it)
        }
        imgCloseIcon?.setOnClickListener {
            etSearchName?.setText("")
        }
    }

    private fun initAdapter() {
        adapter = HomeSearchAdapter (
            { favorite -> addFavorite(favorite) },
            { download-> downloadFilms(download) }
        )
        rvSearchHome.adapter = adapter
    }

    private fun search(edit: Editable?) {
        job?.cancel()
        job = MainScope().launch {
            edit.let {
                if (it.toString().isNotEmpty() && it.toString().length > 1) {
                    rvSearchHome.visibility = View.INVISIBLE
                    tvResultNull.visibility = View.INVISIBLE
                    pbSearch.visibility = View.VISIBLE
                    viewModel.searchInfo(it.toString())
                }
            }
        }
    }

    private fun addFavorite(favorite: FavoriteEntity){
        viewModel.addFavorite(favorite)
    }

    private fun downloadFilms(films: String?){
        viewModel.downloadFilms(films)
    }
}