package com.example.starwars.data.network.models.searchStarships

data class SearchStarships(
	val next: Any? = null,
	val previous: Any? = null,
	val count: Int? = null,
	val results: List<ResultsItem?>? = null
)
