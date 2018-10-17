package edu.gwu.trivia.model.generated.petfinder


import com.squareup.moshi.Json


data class Header(

	@field:Json(name="version")
	val version: Version? = null,

	@field:Json(name="timestamp")
	val timestamp: Timestamp? = null,

	@field:Json(name="status")
	val status: Status? = null
)