package edu.gwu.trivia.model.generated

import com.squareup.moshi.Json

data class BingResponse(

	@Json(name="pivotSuggestions")
	val pivotSuggestions: List<PivotSuggestionsItem?>? = null,

	@Json(name="relatedSearches")
	val relatedSearches: List<RelatedSearchesItem?>? = null,

	@Json(name="readLink")
	val readLink: String? = null,

	@Json(name="totalEstimatedMatches")
	val totalEstimatedMatches: Int? = null,

	@Json(name="_type")
	val type: String? = null,

	@Json(name="webSearchUrl")
	val webSearchUrl: String? = null,

	@Json(name="instrumentation")
	val instrumentation: Instrumentation? = null,

	@Json(name="queryExpansions")
	val queryExpansions: List<QueryExpansionsItem?>? = null,

	@Json(name="nextOffset")
	val nextOffset: Int? = null,

	@Json(name="value")
	val value: List<ValueItem>
)