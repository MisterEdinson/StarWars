package com.example.starwars.domain

import android.util.Log
import com.example.starwars.data.local.dao.FavoriteDao
import com.example.starwars.data.local.dao.FilmsDao
import com.example.starwars.data.local.models.FavoriteEntity
import com.example.starwars.data.local.models.FilmsEntity
import com.example.starwars.data.network.SimpleRetro
import com.example.starwars.data.network.usecase.MappingPeopleToFavorite
import com.example.starwars.data.network.usecase.MappingPlanetsToFavorite
import com.example.starwars.data.network.usecase.MappingStarshipsToFavorite
import com.google.gson.Gson
import java.net.SocketTimeoutException
import javax.inject.Inject

class Repository @Inject constructor(
    private val retrofit: SimpleRetro,
    private val daoFavorite: FavoriteDao,
    private val daoFilms: FilmsDao
) {
    suspend fun search(name: String): List<FavoriteEntity?> {
        try {
            val responsePeople = retrofit.searchPeople(name)
            val responsePlanet = retrofit.searchPlanet(name)
            val responseStarships = retrofit.searchStarships(name)

            val resultPeople = MappingPeopleToFavorite().mappingPeopleToFavorite(responsePeople)
            val resultPlanet = MappingPlanetsToFavorite().mappingPlanetToFavorite(responsePlanet)
            val resultStarship =
                MappingStarshipsToFavorite().mappingStarshipsToFavorite(responseStarships)

            return resultPeople.flatMap {
                listOf(it)
            } + resultPlanet.flatMap {
                listOf(it)
            } + resultStarship.flatMap { listOf(it) }

        } catch (e: SocketTimeoutException) {
            Log.d("SocketTimeoutException:", "timeout")
            return emptyList()
        }
    }

    suspend fun addFavorite(add: FavoriteEntity) {
        daoFavorite.insertFavorite(add)
    }

    suspend fun getFavorite(): List<FavoriteEntity> {
        return daoFavorite.getAllFavorite()
    }

    suspend fun getDetails(url: String): FavoriteEntity {
        return daoFavorite.getItemFavorite(url)
    }

    suspend fun deleteItem(delete: String) {
        daoFavorite.deleteFavorite(delete)
    }

    suspend fun downloadFilms(urls: String?) {
        val gson = Gson()
        val filmsArr: Array<String> = gson.fromJson(urls, Array<String>::class.java)
        filmsArr.forEach {

            val link = it.substring(28) + it.substring(29, it.length - 1)
            val films = retrofit.searchFilm(link)
            if (films != null) {
                val response = FilmsEntity(
                    id = 0,
                    edited = films.edited,
                    director = films.director,
                    created = films.created,
                    vehicles = films.vehicles.toString(),
                    openingCrawl = films.openingCrawl,
                    title = films.title,
                    url = films.url,
                    characters = films.characters.toString(),
                    episodeId = films.episodeId,
                    planets = films.planets.toString(),
                    releaseDate = films.releaseDate,
                    starships = films.starships.toString(),
                    species = films.species.toString(),
                    producer = films.producer,
                )
                daoFilms.insertFilm(response)
            }
        }
    }

    suspend fun getFilmsSearch(urls: Array<String>): List<FilmsEntity> {
        return daoFilms.getFilms(urls)
    }
}