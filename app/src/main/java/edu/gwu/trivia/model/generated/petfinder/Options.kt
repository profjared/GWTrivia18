package edu.gwu.trivia.model.generated.petfinder


import com.squareup.moshi.Json


data class Options(

	@field:Json(name="option")
	val option: List<OptionItem?>? = null
)