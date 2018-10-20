package edu.gwu.trivia.model.generated.petfinder

import com.squareup.moshi.Json

data class Contact(
        @Json(name = "email") val email: StringWrapper,
        @Json(name = "zip") val zip: StringWrapper
)
