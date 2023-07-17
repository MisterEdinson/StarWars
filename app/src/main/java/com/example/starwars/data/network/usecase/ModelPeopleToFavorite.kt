package com.example.starwars.data.network.usecase

import com.example.starwars.data.local.models.FavoriteEntity
import com.example.starwars.data.network.models.searchPeople.ResultsItem
import com.google.gson.Gson

class ModelPeopleToFavorite {
    fun mapping(model: ResultsItem?): FavoriteEntity {
        val gson = Gson()
        return FavoriteEntity(
            id = 0,
            name = model?.name,
            height = model?.height,
            mass = model?.mass,
            birth = model?.birthYear,
            gender = model?.gender,
            homeworld = model?.homeworld,
            films = gson.toJson(model?.films),
            species = gson.toJson(model?.species),
            vehicles = gson.toJson(model?.vehicles),
            starships = model?.starships?.size.toString(),
            url = model?.url,
        )
    }
}