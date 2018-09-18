package edu.gwu.trivia

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer(val answer: String, private val correct: Boolean): Parcelable