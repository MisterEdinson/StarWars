package com.example.starwars.data.local.dao

import androidx.room.*
import com.example.starwars.data.local.models.FavoriteEntity

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM starwars_favorite_person")
    suspend fun getAllFavorite(): List<FavoriteEntity>

    @Query("SELECT * FROM starwars_favorite_person WHERE name LIKE :search")
    suspend fun getItemFavorite(search: String): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(insert: FavoriteEntity)

    @Query("DELETE FROM starwars_favorite_person WHERE name LIKE :delete")
    suspend fun deleteFavorite(delete: String)

    @Query("DELETE FROM starwars_favorite_person")
    suspend fun deleteAll()
}