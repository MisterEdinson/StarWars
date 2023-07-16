package com.example.starwars.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.data.local.models.FavoriteEntity
import com.example.starwars.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    val searchPeopleData: MutableLiveData<List<FavoriteEntity?>> = MutableLiveData()

    fun searchInfo(name: String) {
        viewModelScope.launch {
            val response = repo.search(name)
            searchPeopleData.value = response
        }
    }

    fun addFavorite(favorite:FavoriteEntity){
        viewModelScope.launch {
            repo.addFavorite(favorite)
        }
    }
}