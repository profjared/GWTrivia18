package edu.gwu.trivia

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import edu.gwu.trivia.model.Score
import java.io.IOException
import java.util.*

class PersistenceManager(private val context: Context) {
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun saveScore(score: Int) {

        val scores = fetchScores().toMutableList()
        val score = Score(score, Date())

        scores.add(score)

        val editor = sharedPreferences.edit()

        //convert a list of scores into a JSON string
        val moshi = Moshi.Builder().add(Date::class.java, Rfc3339DateJsonAdapter()).build()
        val listType = Types.newParameterizedType(List::class.java, Score::class.java)
        val jsonAdapter = moshi.adapter<List<Score>>(listType)
        val jsonString = jsonAdapter.toJson(scores)

        editor.putString(Constants.SCORES_PREF_KEY, jsonString)

        editor.apply()

    }

    fun fetchScores(): List<Score> {

        val jsonString = sharedPreferences.getString(Constants.SCORES_PREF_KEY, null)

        //if null, this means no previous scores, so create an empty array list
        if(jsonString == null) {
            return arrayListOf<Score>()
        }
        else {
            //existing scores, so convert the scores JSON string into Score objects, using Moshi
            val listType = Types.newParameterizedType(List::class.java, Score::class.java)
            val moshi = Moshi.Builder()
                    .add(Date::class.java, Rfc3339DateJsonAdapter())
                    .build()
            val jsonAdapter = moshi.adapter<List<Score>>(listType)

            var scores:List<Score>? = emptyList<Score>()
            try {
                scores = jsonAdapter.fromJson(jsonString)
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, e.message)
            }

            if(scores != null) {
                return scores.sortedByDescending { it.score }
            }
            else {
                return emptyList<Score>()
            }
        }
    }

    fun highScore(): Score? {
        val scores = fetchScores()

        return scores.firstOrNull()
    }
}