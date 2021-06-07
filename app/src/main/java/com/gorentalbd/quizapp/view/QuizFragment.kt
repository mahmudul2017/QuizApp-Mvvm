package com.gorentalbd.quizapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.gorentalbd.quizapp.R

class QuizFragment : Fragment() {

    var word = ""
    var score = 0
    private lateinit var wordList: MutableList<String>
    private lateinit var wordText: TextView
    private lateinit var scoreText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)

        resetList()

        wordText = view.findViewById(R.id.word_text)
        scoreText = view.findViewById(R.id.score_text)

        val skip: Button = view.findViewById(R.id.skip_button)
        skip.setOnClickListener { onSkip() }

        val correctButton: Button = view.findViewById(R.id.correct_button)
        correctButton.setOnClickListener { correct() }

        val endGame: Button = view.findViewById(R.id.end_game_button)
        endGame.setOnClickListener { endGame() }

        return view
    }

    private fun endGame() {
        val action = GameFragmentDirections.actionGameFragmentToScoreFramgent()
        action.score = score
        findNavController().navigate(action)
    }

    private fun correct() {
        if (wordList.isNotEmpty()) {
            score++
            word = wordList.removeAt(0)
        } else {
            endGame()
        }
        wordText.text = word
        scoreText.text = score.toString()
    }

    private fun onSkip() {
        score--
        if (wordList.isNotEmpty()) {
            word = wordList.removeAt(0)
        }
        wordText.text = word
        scoreText.text = score.toString()
    }

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }
}