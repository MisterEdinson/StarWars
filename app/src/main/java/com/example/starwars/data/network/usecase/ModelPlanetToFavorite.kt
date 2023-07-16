package com.example.starwars.data.network.usecase

import com.example.starwars.data.local.models.PersonFavoriteEntity
import com.example.starwars.data.network.models.searchPlanets.ResultsItem
import com.google.gson.Gson

class ModelPlanetToFavorite {
    fun mapping(model: ResultsItem?): PersonFavoriteEntity {
        val gson = Gson()
        return PersonFavoriteEntity(
            id = 0,
            name = model?.name,
            films = gson.toJson(model?.films),
            url = model?.url,
            rotationPeriod = model?.rotationPeriod,
            population = model?.population,
            orbitalPeriod = model?.orbitalPeriod,
            surfaceWater = model?.surfaceWater,
            diameter = model?.diameter,
            gravity = model?.gravity,
            residents = gson.toJson(model?.residents),
            terrain = model?.terrain
        )
    }
}