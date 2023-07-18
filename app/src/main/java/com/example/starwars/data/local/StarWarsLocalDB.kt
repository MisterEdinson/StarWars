package com.example.starwars.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.starwars.data.local.dao.FavoriteDao
import com.example.starwars.data.local.dao.FilmsDao
import com.example.starwars.data.local.models.FavoriteEntity
import com.example.starwars.data.local.models.FilmsEntity

@Database(entities = [
    FavoriteEntity::class,
    FilmsEntity::class], version = 1, exportSchema = false)
abstract class StarWarsLocalDB : RoomDatabase() {
    abstract fun favoritePerson(): FavoriteDao
    abstract fun filmsAdd() : FilmsDao
}