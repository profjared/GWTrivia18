package edu.gwu.trivia.model.generated.petfinder


import com.squareup.moshi.Json


data class PhotoItem(

	@field:Json(name="\$t")
	val T: String? = null,

	@field:Json(name="@size")
	val size: String? = null,

	@field:Json(name="@id")
	val id: String? = null
)