package com.example.starwars.data.network.models.searchFilms

data class SearchFilms(
	val next: Any? = null,
	val previous: Any? = null,
	val count: Int? = null,
	val results: List<ResultsItem?>? = null
)
