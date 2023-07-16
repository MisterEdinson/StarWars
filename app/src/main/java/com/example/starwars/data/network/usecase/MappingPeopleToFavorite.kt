package com.example.starwars.data.network.usecase

import com.example.starwars.data.local.models.PersonFavoriteEntity
import com.example.starwars.data.network.models.searchPeople.ResultsItem
import com.example.starwars.data.network.models.searchPeople.SearchPeople

class MappingPeopleToFavorite {
    fun mappingPeopleToFavorite(response: SearchPeople?): List<PersonFavoriteEntity> {
        val model: List<ResultsItem?>? = response?.results
        val mapper = ModelPeopleToFavorite()
        val mapping: List<PersonFavoriteEntity> =
            model?.mapNotNull { item ->
                item.let {
                    mapper.mapping(it)
                }
            } ?: emptyList()
        return mapping
    }
}