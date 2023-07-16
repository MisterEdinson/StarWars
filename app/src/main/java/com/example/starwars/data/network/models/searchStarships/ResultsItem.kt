package com.example.starwars.data.network.models.searchStarships

data class ResultsItem(
	val maxAtmospheringSpeed: String? = null,
	val cargoCapacity: String? = null,
	val films: List<String?>? = null,
	val passengers: String? = null,
	val pilots: List<Any?>? = null,
	val edited: String? = null,
	val consumables: String? = null,
	val mGLT: String? = null,
	val created: String? = null,
	val length: String? = null,
	val starshipClass: String? = null,
	val url: String? = null,
	val manufacturer: String? = null,
	val crew: String? = null,
	val hyperdriveRating: String? = null,
	val costInCredits: String? = null,
	val name: String? = null,
	val model: String? = null
)
