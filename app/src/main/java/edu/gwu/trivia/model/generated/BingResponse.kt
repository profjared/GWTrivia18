package edu.gwu.trivia.model.generated

import com.squareup.moshi.Json

data class BingResponse(

	@field:Json(name="pivotSuggestions")
	val pivotSuggestions: List<PivotSuggestionsItem?>? = null,

	@field:Json(name="relatedSearches")
	val relatedSearches: List<RelatedSearchesItem?>? = null,

	@field:Json(name="readLink")
	val readLink: String? = null,

	@field:Json(name="totalEstimatedMatches")
	val totalEstimatedMatches: Int? = null,

	@field:Json(name="_type")
	val type: String? = null,

	@field:Json(name="webSearchUrl")
	val webSearchUrl: String? = null,

	@field:Json(name="instrumentation")
	val instrumentation: Instrumentation? = null,

	@field:Json(name="queryExpansions")
	val queryExpansions: List<QueryExpansionsItem?>? = null,

	@field:Json(name="nextOffset")
	val nextOffset: Int? = null,

	@field:Json(name="value")
	val value: List<ValueItem>
)