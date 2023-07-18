package com.example.starwars.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwars.data.local.models.FavoriteEntity
import com.example.starwars.data.local.models.FilmsEntity

@Dao
interface FilmsDao {
    @Query("SELECT * FROM films_favorite")
    suspend fun getAllFilms(): List<FilmsEntity>

    @Query("SELECT * FROM films_favorite WHERE url LIKE :url")
    suspend fun searchFilms(url: String): FilmsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: FilmsEntity)

    @Query("SELECT * FROM films_favorite WHERE url IN (:urls)")
    suspend fun getFilms(urls: Array<String>): List<FilmsEntity>
}