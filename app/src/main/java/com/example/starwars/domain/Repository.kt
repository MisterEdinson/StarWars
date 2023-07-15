package com.example.starwars.domain

import com.example.starwars.data.network.SimpleRetro
import com.example.starwars.data.network.models.searchPeople.SearchPeople
import javax.inject.Inject

class Repository @Inject constructor(
    private val retrofit: SimpleRetro
) {
    suspend fun searchPeople(name: String): SearchPeople? {
        return retrofit.searchPeople(name)
    }
}