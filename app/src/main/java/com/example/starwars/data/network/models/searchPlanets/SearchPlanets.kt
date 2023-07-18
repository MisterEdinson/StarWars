package com.example.starwars.data.network.models.searchPlanets

data class SearchPlanets(
	val next: Any? = null,
	val previous: Any? = null,
	val count: Int? = null,
	val results: List<ResultsItem?>? = null
)
