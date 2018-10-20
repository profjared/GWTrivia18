package edu.gwu.trivia.model.generated.petfinder

import com.squareup.moshi.Json

data class Breeds(@Json(name = "breed") val breed: List<StringWrapper>)