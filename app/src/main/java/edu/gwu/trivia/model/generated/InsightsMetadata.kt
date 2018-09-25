package edu.gwu.trivia.model.generated

import com.squareup.moshi.Json

data class InsightsMetadata(

	@Json(name="recipeSourcesCount")
	val recipeSourcesCount: Int? = null,

	@Json(name="pagesIncludingCount")
	val pagesIncludingCount: Int? = null,

	@Json(name="availableSizesCount")
	val availableSizesCount: Int? = null,

	@Json(name="bestRepresentativeQuery")
	val bestRepresentativeQuery: BestRepresentativeQuery? = null
)