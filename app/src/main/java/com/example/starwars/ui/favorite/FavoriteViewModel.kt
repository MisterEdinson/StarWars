package com.example.starwars.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.data.local.models.FavoriteEntity
import com.example.starwars.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    val favoriteData: MutableLiveData<List<FavoriteEntity>> = MutableLiveData()

    fun getFavoriteAll(){
        viewModelScope.launch {
            val response = repository.getFavorite()
            favoriteData.value = response
        }
    }
}