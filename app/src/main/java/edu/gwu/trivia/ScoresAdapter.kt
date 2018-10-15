package edu.gwu.trivia

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.gwu.trivia.model.Score

class ScoresAdapter(private val scores: List<Score>):
        RecyclerView.Adapter<ScoresAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup?.context)

        return ViewHolder(layoutInflater.inflate(R.layout.row_score, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return scores.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val score = scores.get(position)

        viewHolder.bind(score)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val scoreTextView: TextView = view.findViewById(R.id.score_textview)
        private val dateTextView: TextView = view.findViewById(R.id.date_textview)

        fun bind(score: Score) {
            scoreTextView.text = score.score.toString()
            dateTextView.text = score.date.toString()
        }
    }
}


