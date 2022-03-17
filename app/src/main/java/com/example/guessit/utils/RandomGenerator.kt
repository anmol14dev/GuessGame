package com.example.guessit.utils
import android.graphics.Color
import com.example.guessit.model.GameItemModel

class RandomGenerator {

    companion object {

        fun generateRandomArray(userInput:Number): List<GameItemModel> {
            var generatedList: MutableList<GameItemModel> = MutableList(8){ GameItemModel("Wrong",true,false) }
            generatedList.add(GameItemModel(userInput.toString(),true,true))
            var randomList= generatedList.shuffled()
            return randomList
        }
    }

}