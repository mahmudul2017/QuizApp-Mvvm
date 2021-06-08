package com.gorentalbd.quizapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.gorentalbd.quizapp.R
import com.gorentalbd.quizapp.viewmodel.QuizViewModel

@Suppress("DEPRECATION")
class QuizFragment : Fragment() {

    private lateinit var wordText: TextView
    private lateinit var scoreText: TextView
    private lateinit var answerText: TextView
    private lateinit var showText: TextView

    private lateinit var quizViewModel: QuizViewModel
    private var viewAnswerValue = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
        quizViewModel.resetList()

        wordText = view.findViewById(R.id.word_text)
        scoreText = view.findViewById(R.id.score_text)

        val skip: Button = view.findViewById(R.id.skip_button)
        skip.setOnClickListener { onSkip() }

        val correctButton: Button = view.findViewById(R.id.correct_button)
        correctButton.setOnClickListener { correct() }

        val endGame: Button = view.findViewById(R.id.end_game_button)
        endGame.setOnClickListener { endGame() }

        answerText = view.findViewById(R.id.view_answer_button)
        showText = view.findViewById(R.id.view_answer_show)
        answerText.setOnClickListener { viewAnswer(viewAnswerValue) }

        quizViewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            /*if (quizViewModel.wordList.isNotEmpty()) {
                quizViewModel.score++
                quizViewModel.word = quizViewModel.wordList.removeAt(0)
            } else {
                endGame()
            }
            wordText.text = quizViewModel.word
            scoreText.text = quizViewModel.score.toString()*/

            Toast.makeText(context, "called liveData ${it[0]}", Toast.LENGTH_LONG).show()
            Log.d("calledLiveData1", "$it")
        })

        quizViewModel.wordClickData.observe(viewLifecycleOwner, Observer {
            viewAnswerValue = it
            Toast.makeText(context, "you clicked at item: ${it}", Toast.LENGTH_LONG).show()
            Log.d("calledLiveData2", "$it")
        })
    }

    private fun viewAnswer(answer: String) {
        if (answer.isNullOrBlank()) {
            showText.visibility = View.GONE
            Toast.makeText(context, "Please select a word first", Toast.LENGTH_LONG).show()
        } else {
            showText.visibility = View.VISIBLE
            showText.text = answer
        }
    }

    private fun endGame() {
        val action = QuizFragmentDirections.actionGameFragmentToScoreFramgent()
        action.score = quizViewModel.score
        findNavController().navigate(action)
    }

    private fun correct() {
        showText.visibility = View.GONE
        if (quizViewModel.wordList.isNotEmpty()) {
            quizViewModel.correctAnswer()
        } else {
            endGame()
        }
        wordText.text = quizViewModel.word
        scoreText.text = quizViewModel.score.toString()
    }

    private fun onSkip() {
        quizViewModel.score--
        if (quizViewModel.wordList.isNotEmpty()) {
            quizViewModel.word = quizViewModel.wordList.removeAt(0)
        }
        wordText.text = quizViewModel.word
        scoreText.text = quizViewModel.score.toString()
    }
}