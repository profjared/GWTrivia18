package edu.gwu.trivia.model.generated.petfinder

import com.squareup.moshi.Json

data class Pets(@Json(name = "pet") val pet: List<PetItem>)