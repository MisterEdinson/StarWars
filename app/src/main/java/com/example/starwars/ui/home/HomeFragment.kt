package com.example.starwars.ui.home

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.starwars.R
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
        viewModel.searchPeopleData.observe(viewLifecycleOwner, Observer {
            adapter?.list?.submitList(it)
        })
        etSearchName.addTextChangedListener{
            search(it)
        }
    }

    private fun initAdapter() {
        adapter = HomeSearchAdapter()
        rvSearchHome.adapter = adapter
    }

    private fun search(edit:Editable?){
        job?.cancel()
        job = MainScope().launch {
            edit.let {
                if(it.toString().isNotEmpty() && it.toString().length > 1){
                    viewModel.searchPeople(it.toString())
                }
            }
        }
    }
}