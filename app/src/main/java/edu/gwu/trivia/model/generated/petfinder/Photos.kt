package edu.gwu.trivia.model.generated.petfinder

import com.squareup.moshi.Json

data class Photos(@Json(name = "photo") val photo: List<PhotoItem>)