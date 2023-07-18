package com.example.starwars.data.network

import com.example.starwars.data.network.models.Films
import com.example.starwars.data.network.models.searchPeople.SearchPeople
import com.example.starwars.data.network.models.searchPlanets.SearchPlanets
import com.example.starwars.data.network.models.searchStarships.SearchStarships
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleRetro {
    @GET("people/")
    suspend fun searchPeople(
        @Query("search") name: String
    ): SearchPeople?

    @GET("planets/")
    suspend fun searchPlanet(
        @Query("search") name: String
    ): SearchPlanets?

    @GET("starships/")
    suspend fun searchStarships(
        @Query("search") name: String
    ): SearchStarships?

    @GET("films/{url}")
    suspend fun searchFilm(@Path("url") film: String): Films?
}