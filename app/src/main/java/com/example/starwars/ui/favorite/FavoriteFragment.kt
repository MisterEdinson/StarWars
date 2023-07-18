package com.example.starwars.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.starwars.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.*

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val viewModel by viewModels<FavoriteViewModel>()
    private var adapter: FavoriteAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.getFavoriteAll()
        viewModel.favoriteData.observe(viewLifecycleOwner) {
            adapter?.list?.submitList(it)
        }
    }

    private fun initAdapter(){
        adapter = FavoriteAdapter({delete -> deleteFavorite(delete)})
        rvFavorite.adapter = adapter
    }

    private fun deleteFavorite(delete: String){
        viewModel.deleteFavoriteItem(delete)
        viewModel.getFavoriteAll()
    }
}