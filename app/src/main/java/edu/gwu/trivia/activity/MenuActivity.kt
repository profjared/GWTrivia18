package edu.gwu.trivia.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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

        setSupportActionBar(menu_toolbar)

        persistenceManager = PersistenceManager(this)

        play_button.setOnClickListener {
            Log.d(TAG, "play button tapped")

            loadGameData()
        }

        high_scores_button.setOnClickListener {
            val intent = Intent(this@MenuActivity, ScoreActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val highScore = persistenceManager.highScore()?.score ?: 0 //fall back on 0
        high_score_textview.text = getString(R.string.score, highScore)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_menu, menu)

        return true
    }

    fun shareButtonPressed(item: MenuItem) {
        val sendIntent = Intent()

        sendIntent.action = Intent.ACTION_SEND

        val highScore = persistenceManager.highScore()?.score ?: 0 //fall back on 0
        val shareText = getString(R.string.share_message, highScore)
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareText)
        sendIntent.type = "text/plain"

        startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.share)))
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
