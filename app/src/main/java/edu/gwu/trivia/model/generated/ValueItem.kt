package edu.gwu.trivia.model.generated

import com.squareup.moshi.Json

data class ValueItem(

	@field:Json(name="insightsMetadata")
	val insightsMetadata: InsightsMetadata? = null,

	@field:Json(name="hostPageUrl")
	val hostPageUrl: String? = null,

	@field:Json(name="thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:Json(name="imageId")
	val imageId: String? = null,

	@field:Json(name="accentColor")
	val accentColor: String? = null,

	@field:Json(name="imageInsightsToken")
	val imageInsightsToken: String? = null,

	@field:Json(name="webSearchUrl")
	val webSearchUrl: String? = null,

	@field:Json(name="datePublished")
	val datePublished: String? = null,

	@field:Json(name="hostPageDisplayUrl")
	val hostPageDisplayUrl: String? = null,

	@field:Json(name="contentUrl")
	val contentUrl: String,

	@field:Json(name="contentSize")
	val contentSize: String,

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="width")
	val width: Int,

	@field:Json(name="encodingFormat")
	val encodingFormat: String? = null,

	@field:Json(name="thumbnailUrl")
	val thumbnailUrl: String? = null,

	@field:Json(name="height")
	val height: Int,

	@field:Json(name="creativeCommons")
	val creativeCommons: String? = null
)