package com.example.starwars.domain

import com.example.starwars.data.local.dao.FavoriteDao
import com.example.starwars.data.local.models.FavoriteEntity
import com.example.starwars.data.network.SimpleRetro
import com.example.starwars.data.network.usecase.MappingPeopleToFavorite
import com.example.starwars.data.network.usecase.MappingPlanetsToFavorite
import com.example.starwars.data.network.usecase.MappingStarshipsToFavorite
import javax.inject.Inject

class Repository @Inject constructor(
    private val retrofit: SimpleRetro,
    private val daoFavorite: FavoriteDao
) {
    suspend fun search(name: String): List<FavoriteEntity?> {
        val responsePeople = retrofit.searchPeople(name)
        val responsePlanet = retrofit.searchPlanet(name)
        val responseStarships = retrofit.searchStarships(name)

        val resultPeople = MappingPeopleToFavorite().mappingPeopleToFavorite(responsePeople)
        val resultPlanet = MappingPlanetsToFavorite().mappingPlanetToFavorite(responsePlanet)
        val resultStarship =
            MappingStarshipsToFavorite().mappingStarshipsToFavorite(responseStarships)

        return resultPeople.flatMap {
            it.let { listOf(it) } ?: emptyList()
        } + resultPlanet.flatMap {
            it.let { listOf(it) } ?: emptyList()
        } + resultStarship.flatMap { it.let { listOf(it) } ?: emptyList() }
    }

    suspend fun addFavorite(add: FavoriteEntity) {
        daoFavorite.insertFavorite(add)
    }

    suspend fun getFavorite(): List<FavoriteEntity> {
        return daoFavorite.getAllFavorite()
    }

    suspend fun getDetails(url: String) : FavoriteEntity{
        return daoFavorite.getItemFavorite(url)
    }
}