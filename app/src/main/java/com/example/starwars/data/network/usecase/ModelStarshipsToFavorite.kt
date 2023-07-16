package com.example.starwars.data.network.usecase

import com.example.starwars.data.local.models.FavoriteEntity
import com.example.starwars.data.network.models.searchStarships.ResultsItem
import com.google.gson.Gson

class ModelStarshipsToFavorite {
    fun mapping(model: ResultsItem?): FavoriteEntity {
        val gson = Gson()
        return FavoriteEntity(
            id = 0,
            name = model?.name,
            films = gson.toJson(model?.films),
            model = model?.model,
            manufacturer = model?.manufacturer,
            credits = model?.costInCredits,
            lenght = model?.length,
            speed = model?.maxAtmospheringSpeed,
            crew = model?.crew,
            passengers = model?.passengers,
            cargocapacity = model?.cargoCapacity,
            consumables = model?.consumables,
            mGLT = model?.mGLT,
            starshipClass = model?.starshipClass,
            pilots = gson.toJson(model?.pilots),
            url = model?.url,
        )
    }
}