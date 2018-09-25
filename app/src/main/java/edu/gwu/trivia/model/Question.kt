package edu.gwu.trivia.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(val wrongAnswers: List<Answer>, val correctAnswer: Answer): Parcelable