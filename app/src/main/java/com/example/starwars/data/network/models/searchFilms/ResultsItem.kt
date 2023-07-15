package com.example.starwars.data.network.models.searchFilms

data class ResultsItem(
	val edited: String? = null,
	val director: String? = null,
	val created: String? = null,
	val vehicles: List<String?>? = null,
	val openingCrawl: String? = null,
	val title: String? = null,
	val url: String? = null,
	val characters: List<String?>? = null,
	val episodeId: Int? = null,
	val planets: List<String?>? = null,
	val releaseDate: String? = null,
	val starships: List<String?>? = null,
	val species: List<String?>? = null,
	val producer: String? = null
)
