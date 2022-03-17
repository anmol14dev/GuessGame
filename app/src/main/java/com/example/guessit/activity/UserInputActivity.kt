package com.example.guessit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.guessit.R
import com.example.guessit.databinding.ActivityUserInputBinding
import com.example.guessit.viewModel.UserInputViewModel

class UserInputActivity : AppCompatActivity() {
    private lateinit var activityUserInputBinding: ActivityUserInputBinding
    private lateinit var userInputViewModel: UserInputViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }
     fun init(){
         activityUserInputBinding=DataBindingUtil.setContentView(this,R.layout.activity_user_input)
         userInputViewModel=ViewModelProvider(this).get(UserInputViewModel::class.java)
         activityUserInputBinding.lifecycleOwner=this
         activityUserInputBinding.userInputVM=userInputViewModel
         activityUserInputBinding.generateButton.setOnClickListener {
             if(userInputViewModel.checkInput())
             {
                 var intent = Intent(this, GameScreenActivity::class.java)
                 intent.putExtra("inputByUser",userInputViewModel.inputText.value)
                 startActivity(intent)
                 finish()
             }
             else{
                 Toast.makeText(this,"Invalid Input",Toast.LENGTH_LONG).show()
             }

         }
     }

}