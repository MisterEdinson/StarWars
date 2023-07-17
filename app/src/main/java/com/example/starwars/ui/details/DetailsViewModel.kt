package com.example.starwars.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.data.local.models.FavoriteEntity
import com.example.starwars.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor (private val repository: Repository) : ViewModel() {

    val getDetailsInfo: MutableLiveData<FavoriteEntity> = MutableLiveData()

    fun getFromName(url:String){
        viewModelScope.launch {
            val response = repository.getDetails(url)
            getDetailsInfo.value = response
        }
    }
}