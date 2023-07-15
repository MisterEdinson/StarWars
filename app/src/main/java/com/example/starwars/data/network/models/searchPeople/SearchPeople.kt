package com.example.starwars.data.network.models.searchPeople

data class SearchPeople(
	val next: String? = null,
	val previous: Any? = null,
	val count: Int? = null,
	val results: List<ResultsItem?>? = null
)
