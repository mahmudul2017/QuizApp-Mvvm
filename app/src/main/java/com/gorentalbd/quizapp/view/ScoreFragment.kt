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

class ScoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_score, container, false)

        val scoreText: TextView = view.findViewById(R.id.score_text)

        val score = ScoreFragmentArgs.fromBundle(requireArguments()).score.toString()
        scoreText.text = score

        val playAgin: Button = view.findViewById(R.id.play_again_button)
        playAgin.setOnClickListener { findNavController().navigate(ScoreFragmentDirections.actionRestart()) }

        return  view
    }
}