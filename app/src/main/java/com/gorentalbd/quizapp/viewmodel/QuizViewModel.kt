package com.gorentalbd.quizapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel() {
    var word = ""
    var score = 0
    lateinit var wordList: MutableList<String>
    var wordLiveData: MutableLiveData<List<String>> = MutableLiveData()
    var wordClickData: MutableLiveData<String> = MutableLiveData()

    fun resetList() {
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

    fun correctAnswer() {
        score++
        wordClickData.value = wordList[0]
        word = wordList.removeAt(0)
        wordLiveData.value = wordList
    }

    fun getLiveData(): LiveData<List<String>> {
        return wordLiveData
    }
}