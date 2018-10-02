package edu.gwu.trivia

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class PersistenceManager(private val context: Context) {
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun saveScore(score: Int) {
        val editor = sharedPreferences.edit()

        editor.putInt(Constants.SCORES_PREF_KEY, score)
        editor.apply()

    }

    fun fetchScore(): Int {
        return sharedPreferences.getInt(Constants.SCORES_PREF_KEY, 0)
    }

}