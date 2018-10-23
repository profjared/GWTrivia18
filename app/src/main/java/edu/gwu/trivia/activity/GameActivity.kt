package edu.gwu.trivia.activity

import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import edu.gwu.trivia.*
import edu.gwu.trivia.model.GameData
import edu.gwu.trivia.model.Score
import kotlinx.android.synthetic.main.activity_game.*
import org.jetbrains.anko.toast
import java.util.*

class GameActivity : AppCompatActivity(), BingImageSearchManager.ImageSearchCompletionListener, ShakeDetector.ShakeListener, LocationDetector.LocationListener {
    private val TAG = "GameActivity"
    private lateinit var gameData: GameData
    private lateinit var bingImageSearchManager: BingImageSearchManager
    private lateinit var persistenceManager: PersistenceManager
    private lateinit var shakeDetector: ShakeDetector
    private lateinit var locationDetector: LocationDetector

    private var score = 0
    private var currentQuestionIndex = 0
    private var numWrong = 0
    private var skipUsed = false

    private var buttons = ArrayList<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        setSupportActionBar(game_toolbar)

        locationDetector = LocationDetector(this)
        locationDetector.locationListener = this

        shakeDetector = ShakeDetector(this)
        shakeDetector.shakeListener = this

        persistenceManager = PersistenceManager(this)

        buttons.apply {
            add(top_left_button)
            add(top_right_button)
            add(bottom_right_button)
            add(bottom_left_button)
        }

        bingImageSearchManager = BingImageSearchManager(this,image_view)
        bingImageSearchManager.imageSearchCompletionListener = this

        gameData = intent.getParcelableExtra<GameData>(MenuActivity.GAME_DATA_KEY)
        Log.d(TAG, "we passed game data with ${gameData.questions.size} questions")

        nextTurn()
    }

    override fun onResume() {
        super.onResume()

        shakeDetector.start()
    }

    override fun onPause() {
        super.onPause()

        shakeDetector.stop()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game, menu)

        return super.onCreateOptionsMenu(menu)
    }

    fun skipButtonPressed(item: MenuItem) {
        skip()
    }

    private fun nextTurn() {
        disableButtons()

        if(numWrong == 3 ) { //game over condition
            locationDetector.detectLocation()
        }
        else {
            supportActionBar?.title = getString(R.string.score, score)

            currentQuestionIndex++
            currentQuestionIndex %= gameData.questions.size

            val question = gameData.questions.get(currentQuestionIndex)
            val answer = question.correctAnswer

            Log.d(TAG, "the correct answer is ${answer.answer}")

            bingImageSearchManager.searchImages(answer.answer)
        }
    }

    private fun disableButtons() {
        buttons.forEach {
            it.isEnabled = false
            it.text = ""
        }
    }

    private fun displayAnswers() {

        val answers = gameData.questions[currentQuestionIndex].wrongAnswers + gameData.questions[currentQuestionIndex].correctAnswer

        Collections.shuffle(answers)

        for(i in buttons.indices) {
            val answer = answers[i]
            val button = buttons[i]

            button.apply {
                text = answer.answer
                tag = answer.correct
                isEnabled = true
            }
        }
    }

    fun buttonPressed(view: View) {
        val isCorrect = view.tag as Boolean

        if(isCorrect) {
            toast(R.string.correct)
            score++
        }
        else {
            toast(R.string.wrong)
            numWrong++
        }

        nextTurn()
    }

    private fun skip() {
        if(!skipUsed) {
            nextTurn()
            skipUsed = true
        }
        else {
            toast(R.string.skip_used)
        }
    }

    override fun imageLoaded() {
        displayAnswers()
    }

    override fun imageNotLoaded() {
        nextTurn()
    }

    override fun shakeDetected() {
        skip()
    }

    private fun gameOver(location: Location?) {
        //didn't have time, but planned to update score model to include a latitude and longitude
        val score = Score(score, Date())

        persistenceManager.saveScore(score)
        finish()
    }

    override fun locationFound(location: Location) {
        toast("${location.latitude}----${location.longitude}")

        gameOver(location)
    }

    override fun locationNotFound(reason: LocationDetector.FailureReason) {

        when (reason) {
            LocationDetector.FailureReason.TIMEOUT -> toast(getString(R.string.location_not_found))
            LocationDetector.FailureReason.NO_PERMISSION -> toast(getString(R.string.no_location_permission))
        }

        gameOver(null)
    }
}