package com.example.starwars.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.data.network.models.searchPeople.SearchPeople
import com.example.starwars.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    val searchPeopleData: MutableLiveData<SearchPeople?> = MutableLiveData()

    fun searchPeople(name: String) {
        viewModelScope.launch {
            val response = repo.searchPeople(name)
            searchPeopleData.value = response
        }
    }
}