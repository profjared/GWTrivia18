package edu.gwu.trivia.model.generated

import com.squareup.moshi.Json

data class PivotSuggestionsItem(

	@field:Json(name="pivot")
	val pivot: String? = null,

	@field:Json(name="suggestions")
	val suggestions: List<SuggestionsItem?>? = null
)