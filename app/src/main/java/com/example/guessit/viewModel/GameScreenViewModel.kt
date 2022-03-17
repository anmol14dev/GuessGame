package com.example.guessit.viewModel

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guessit.model.GameItemModel
import com.example.guessit.utils.RandomGenerator

class GameScreenViewModel : ViewModel() {
    var gameGrid: List<GameItemModel>? = null
    var userInput: Int = 0
    var numberOftry: MutableLiveData<Int> = MutableLiveData(3)
    fun tryAgain() {
        numberOftry.value?.let {
            if (it > 0) {
                numberOftry.value = it - 1
            }

        }

    }

    fun updateVisibility(position: Int) {
        gameGrid?.get(position)?.visible = false
    }

    fun generateGrid() {
        gameGrid = RandomGenerator.generateRandomArray(userInput)

    }
}