package edu.gwu.trivia.model.generated

import com.squareup.moshi.Json

data class PivotSuggestionsItem(

	@Json(name="pivot")
	val pivot: String? = null,

	@Json(name="suggestions")
	val suggestions: List<SuggestionsItem?>? = null
)