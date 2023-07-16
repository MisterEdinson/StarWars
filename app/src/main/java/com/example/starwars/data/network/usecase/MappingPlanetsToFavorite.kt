package com.example.starwars.data.network.usecase

import com.example.starwars.data.local.models.FavoriteEntity
import com.example.starwars.data.network.models.searchPlanets.ResultsItem
import com.example.starwars.data.network.models.searchPlanets.SearchPlanets

class MappingPlanetsToFavorite {
    fun mappingPlanetToFavorite(response: SearchPlanets?): List<FavoriteEntity> {
        val model: List<ResultsItem?>? = response?.results
        val mapper = ModelPlanetToFavorite()
        val mapping: List<FavoriteEntity> =
            model?.mapNotNull { item ->
                item.let {
                    mapper.mapping(it)
                }
            } ?: emptyList()
        return mapping
    }
}