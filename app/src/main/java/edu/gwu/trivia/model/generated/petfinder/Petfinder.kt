package edu.gwu.trivia.model.generated.petfinder


import com.squareup.moshi.Json


data class Petfinder(

	@field:Json(name="pets")
	val pets: Pets? = null,

	@field:Json(name="@xmlns:xsi")
	val xmlnsXsi: String? = null,

	@field:Json(name="@xsi:noNamespaceSchemaLocation")
	val xsiNoNamespaceSchemaLocation: String? = null,

	@field:Json(name="lastOffset")
	val lastOffset: LastOffset? = null,

	@field:Json(name="header")
	val header: Header? = null
)