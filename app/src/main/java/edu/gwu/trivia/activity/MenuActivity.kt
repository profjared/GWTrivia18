package edu.gwu.trivia.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import edu.gwu.trivia.PersistenceManager
import edu.gwu.trivia.R
import edu.gwu.trivia.Utilities
import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync

class MenuActivity : AppCompatActivity() {
    private val TAG = "MenuActivity"
    companion object {
        val GAME_DATA_KEY = "gameData"
    }

    private lateinit var persistenceManager: PersistenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        persistenceManager = PersistenceManager(this)

        play_button.setOnClickListener {
            Log.d(TAG, "play button tapped")

            loadGameData()
        }
    }

    override fun onResume() {
        super.onResume()

        high_score_textview.text = getString(R.string.score, persistenceManager.fetchScore())
    }

    private fun loadGameData() {
        //main thread

        doAsync {
            //background thread
            val gameData = Utilities.loadGameData("presidents.csv", this@MenuActivity)
            Log.d(TAG, "we loaded game data with ${gameData.questions.size} questions")

            activityUiThread {
                //main thread
                val intent = Intent(this@MenuActivity, GameActivity::class.java)
                intent.putExtra(GAME_DATA_KEY, gameData)
                startActivity(intent)
            }
        }


    }
}
