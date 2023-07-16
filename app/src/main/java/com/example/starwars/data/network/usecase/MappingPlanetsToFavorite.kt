package com.example.starwars.data.network.usecase

import com.example.starwars.data.local.models.PersonFavoriteEntity
import com.example.starwars.data.network.models.searchPlanets.ResultsItem
import com.example.starwars.data.network.models.searchPlanets.SearchPlanets

class MappingPlanetsToFavorite {
    fun mappingPlanetToFavorite(response: SearchPlanets?): List<PersonFavoriteEntity> {
        val model: List<ResultsItem?>? = response?.results
        val mapper = ModelPlanetToFavorite()
        val mapping: List<PersonFavoriteEntity> =
            model?.mapNotNull { item ->
                item.let {
                    mapper.mapping(it)
                }
            } ?: emptyList()
        return mapping
    }
}