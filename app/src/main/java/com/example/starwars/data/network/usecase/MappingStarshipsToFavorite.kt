package com.example.starwars.data.network.usecase

import com.example.starwars.data.local.models.FavoriteEntity
import com.example.starwars.data.network.models.searchStarships.ResultsItem
import com.example.starwars.data.network.models.searchStarships.SearchStarships

class MappingStarshipsToFavorite {
    fun mappingStarshipsToFavorite(response: SearchStarships?): List<FavoriteEntity> {
        val model: List<ResultsItem?>? = response?.results
        val mapper = ModelStarshipsToFavorite()
        val mapping: List<FavoriteEntity> =
            model?.mapNotNull { item ->
                item.let {
                    mapper.mapping(it)
                }
            } ?: emptyList()
        return mapping
    }
}