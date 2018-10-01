package edu.gwu.trivia.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer(val answer: String, val correct: Boolean): Parcelable