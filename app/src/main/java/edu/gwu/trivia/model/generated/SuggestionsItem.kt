package edu.gwu.trivia.model.generated

import com.squareup.moshi.Json

data class SuggestionsItem(

	@field:Json(name="displayText")
	val displayText: String? = null,

	@field:Json(name="searchLink")
	val searchLink: String? = null,

	@field:Json(name="thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:Json(name="webSearchUrl")
	val webSearchUrl: String? = null,

	@field:Json(name="text")
	val text: String? = null
)