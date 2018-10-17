package edu.gwu.trivia.model.generated.petfinder


import com.squareup.moshi.Json


data class PetFinderResponse(

	@field:Json(name="petfinder")
	val petfinder: Petfinder? = null,

	@field:Json(name="@version")
	val version: String? = null,

	@field:Json(name="@encoding")
	val encoding: String? = null
)