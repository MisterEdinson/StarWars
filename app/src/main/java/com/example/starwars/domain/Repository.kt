package com.example.starwars.domain

import com.example.starwars.data.local.models.PersonFavoriteEntity
import com.example.starwars.data.network.SimpleRetro
import com.example.starwars.data.network.models.searchPeople.SearchPeople
import com.example.starwars.data.network.usecase.MappingPeopleToFavoriteUseCase
import javax.inject.Inject

class Repository @Inject constructor(
    private val retrofit: SimpleRetro
) {
    suspend fun searchPeople(name: String): List<PersonFavoriteEntity?> {
        val responsePeople = retrofit.searchPeople(name)
        return MappingPeopleToFavoriteUseCase().mappingPeopleToFavorite(responsePeople)
    }
}