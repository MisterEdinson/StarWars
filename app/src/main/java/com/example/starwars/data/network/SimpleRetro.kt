package com.example.starwars.data.network

import com.example.starwars.data.network.models.searchPeople.SearchPeople
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleRetro {
    @GET("people/")
    suspend fun searchPeople(
        @Query("search") name:String
    ): SearchPeople
}