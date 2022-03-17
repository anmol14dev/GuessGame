package com.example.guessit.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserInputViewModel : ViewModel() {
    var inputText =MutableLiveData<String>("")

    fun checkInput():Boolean{
        var input= inputText.value
        if(input?.toIntOrNull()==null)
        {
            inputText.value=""
            return false
        }
        else{
            return true
        }
    }

}