package com.example.starwars.data.local

import androidx.room.RoomDatabase
import com.example.starwars.data.local.dao.FavoriteDao

abstract class StarWarsLocalDB : RoomDatabase() {
    abstract fun favoritePerson(): FavoriteDao
}