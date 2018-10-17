package edu.gwu.trivia.model.generated.petfinder


import com.squareup.moshi.Json


data class Pets(

	@field:Json(name="pet")
	val pet: List<PetItem>? = null //using List<PetItem>? instead of PetItem? - if we have a list, we can pretty much guarantee that it won't have null items in it
	//val pet: List<PetItem?>? = null
)