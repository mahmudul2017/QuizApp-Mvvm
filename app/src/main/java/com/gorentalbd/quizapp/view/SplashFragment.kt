package com.gorentalbd.quizapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.gorentalbd.quizapp.R

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        val play: Button = view.findViewById(R.id.play_game_button)

        play.setOnClickListener {
            findNavController().navigate(SplashFragmentDirections.actionGameToScore())
        }

        return view
    }
}