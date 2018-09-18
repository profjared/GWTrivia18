package edu.gwu.trivia

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync

class MenuActivity : AppCompatActivity() {
    private val TAG = "MenuActivity"
    companion object {
        val GAME_DATA_KEY = "gameData"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        play_button.setOnClickListener {
            Log.d(TAG, "play button tapped")

            loadGameData()
        }
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
