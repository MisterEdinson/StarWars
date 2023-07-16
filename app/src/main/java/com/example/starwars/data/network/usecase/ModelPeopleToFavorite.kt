package com.example.starwars.data.network.usecase

import com.example.starwars.data.local.models.PersonFavoriteEntity
import com.example.starwars.data.network.models.searchPeople.ResultsItem
import com.google.gson.Gson

class ModelPeopleToFavorite {
    fun mapping(model: ResultsItem?): PersonFavoriteEntity {
        val gson = Gson()
        return PersonFavoriteEntity(
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
            starships = gson.toJson(model?.starships),
            model = null,
            manufacturer = null,
            credits = null,
            lenght = null,
            speed = null,
            crew = null,
            passengers = null,
            cargocapacity = null,
            consumables = null,
            mGLT = null,
            starshipClass = null,
            url = model?.url,
            climate = null,
            rotationPeriod = null,
            population = null,
            orbitalPeriod = null,
            surfaceWater = null,
            diameter = null,
            gravity = null,
            residents = null,
            terrain = null
        )
    }
}