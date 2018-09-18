package edu.gwu.trivia

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class GameActivity : AppCompatActivity() {
    private val TAG = "GameActivity"
    private lateinit var gameData: GameData

    private var score = 0
    private var currentQuestionIndex = 0
    private var numWrong = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameData = intent.getParcelableExtra<GameData>(MenuActivity.GAME_DATA_KEY)
        Log.d(TAG, "we passed game data with ${gameData.questions.size} questions")

        nextTurn()
    }

    private fun nextTurn() {
        if(numWrong == 3 ) { //game over condition
            //TODO
        }
        else {
            currentQuestionIndex++
            currentQuestionIndex %= gameData.questions.size

            val question = gameData.questions.get(currentQuestionIndex)
            val answer = question.correctAnswer
            val wrongAnswers = question.wrongAnswers

            Log.d(TAG, "the correct answer is ${answer.answer}")
        }

    }
}
