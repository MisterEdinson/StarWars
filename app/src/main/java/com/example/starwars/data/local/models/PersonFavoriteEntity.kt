package com.example.starwars.data.local.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "starwars_favorite_person", indices = [Index(value = ["name"], unique = true)])
data class PersonFavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String? = null,
    val height: String? = null,
    val mass: String? = null,
    val birth: String? = null,
    val gender: String? = null,
    val homeworld: String? = null,
    val films: String? = null,
    val species: String? = null,
    val vehicles: String? = null,
    val starships: String? = null,

    val model: String? = null,
    val manufacturer: String? = null,
    val credits: String? = null,
    val lenght: String? = null,
    val speed: String? = null,
    val crew: String? = null,
    val passengers: String? = null,
    val cargocapacity: String? = null,
    val consumables: String? = null,
    val mGLT: String? = null,
    val starshipClass: String? = null,
    val url: String? = null,

    val climate: String? = null,
    val rotationPeriod: String? = null,
    val population: String? = null,
    val orbitalPeriod: String? = null,
    val surfaceWater: String? = null,
    val diameter: String? = null,
    val gravity: String? = null,
    val residents: String? = null,
    val terrain: String? = null
)