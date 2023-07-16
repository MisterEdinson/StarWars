package com.example.starwars.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.starwars.data.local.dao.FavoriteDao
import com.example.starwars.data.local.models.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class StarWarsLocalDB : RoomDatabase() {
    abstract fun favoritePerson(): FavoriteDao
}