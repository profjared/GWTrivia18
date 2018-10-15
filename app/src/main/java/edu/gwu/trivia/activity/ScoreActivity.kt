package edu.gwu.trivia.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import edu.gwu.trivia.PersistenceManager
import edu.gwu.trivia.R
import edu.gwu.trivia.ScoresAdapter
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {

    private lateinit var persistenceManager: PersistenceManager
    private lateinit var scoresAdapter: ScoresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        persistenceManager = PersistenceManager(this)

        val scores = persistenceManager.fetchScores()

        scoresAdapter = ScoresAdapter(scores)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = scoresAdapter
    }
}
