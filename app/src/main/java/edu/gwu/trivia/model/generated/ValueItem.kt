package edu.gwu.trivia.model.generated

import com.squareup.moshi.Json

data class ValueItem(

	@Json(name="insightsMetadata")
	val insightsMetadata: InsightsMetadata? = null,

	@Json(name="hostPageUrl")
	val hostPageUrl: String? = null,

	@Json(name="thumbnail")
	val thumbnail: Thumbnail? = null,

	@Json(name="imageId")
	val imageId: String? = null,

	@Json(name="accentColor")
	val accentColor: String? = null,

	@Json(name="imageInsightsToken")
	val imageInsightsToken: String? = null,

	@Json(name="webSearchUrl")
	val webSearchUrl: String? = null,

	@Json(name="datePublished")
	val datePublished: String? = null,

	@Json(name="hostPageDisplayUrl")
	val hostPageDisplayUrl: String? = null,

	@Json(name="contentUrl")
	val contentUrl: String,

	@Json(name="contentSize")
	val contentSize: String,

	@Json(name="name")
	val name: String? = null,

	@Json(name="width")
	val width: Int,

	@Json(name="encodingFormat")
	val encodingFormat: String? = null,

	@Json(name="thumbnailUrl")
	val thumbnailUrl: String? = null,

	@Json(name="height")
	val height: Int,

	@Json(name="creativeCommons")
	val creativeCommons: String? = null
)