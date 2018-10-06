package edu.gwu.trivia.model.generated

import com.squareup.moshi.Json

data class InsightsMetadata(

	@field:Json(name="recipeSourcesCount")
	val recipeSourcesCount: Int? = null,

	@field:Json(name="pagesIncludingCount")
	val pagesIncludingCount: Int? = null,

	@field:Json(name="availableSizesCount")
	val availableSizesCount: Int? = null,

	@field:Json(name="bestRepresentativeQuery")
	val bestRepresentativeQuery: BestRepresentativeQuery? = null
)