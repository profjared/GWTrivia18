package edu.gwu.trivia

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameData(val questions: List<Question>, val triviaCategory: String): Parcelable