package com.example.starwars.data.local.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "films_favorite", indices = [Index(value = ["url"], unique = true)])
data class FilmsEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val edited: String? = null,
    val director: String? = null,
    val created: String? = null,
    val vehicles: String? = null,
    val openingCrawl: String? = null,
    val title: String? = null,
    val url: String? = null,
    val characters: String? = null,
    val episodeId: Int? = null,
    val planets: String? = null,
    val releaseDate: String? = null,
    val starships: String? = null,
    val species: String? = null,
    val producer: String? = null
)