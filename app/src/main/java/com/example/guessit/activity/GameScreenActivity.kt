package com.example.guessit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.guessit.adapters.GameGridRecyclerViewAdapter
import com.example.guessit.R
import com.example.guessit.viewModel.GameScreenViewModel
import com.example.guessit.databinding.ActivityGameScreenBinding
import com.example.guessit.listener.RecycleViewClickListener
import com.example.guessit.model.GameItemModel

class GameScreenActivity : AppCompatActivity(), RecycleViewClickListener {
    private lateinit var gameScreenBinding : ActivityGameScreenBinding
    private lateinit var gameScreenViewModel: GameScreenViewModel
    private lateinit var userInputString : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        createAdapter(gameScreenViewModel.gameGrid)
        notifyDataSetChanged()
    }
    fun init(){
        gameScreenBinding=DataBindingUtil.setContentView(this,R.layout.activity_game_screen)
        gameScreenViewModel=ViewModelProvider(this).get(GameScreenViewModel::class.java)
        gameScreenBinding.gameMatrix.layoutManager= GridLayoutManager(this,3)
        userInputString = intent?.getStringExtra("inputByUser")!!
        if(gameScreenViewModel.gameGrid==null) {
            gameScreenViewModel.userInput=userInputString.toInt()
            gameScreenViewModel.generateGrid()
        }
        gameScreenBinding.lifecycleOwner=this
        gameScreenBinding.gameScreenVM=gameScreenViewModel

    }
    fun resetGame(){
        var intent= Intent(this,UserInputActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun notifyDataSetChanged(){
        gameScreenBinding.gameMatrix.adapter?.notifyDataSetChanged()

    }
    fun createAdapter(grid: List<GameItemModel>?){
        gameScreenBinding.gameMatrix.adapter=
            grid?.let { GameGridRecyclerViewAdapter(it,this) }
    }
    fun reshuffle(){
        gameScreenViewModel.userInput=userInputString.toInt()
        gameScreenViewModel.generateGrid()
        createAdapter(gameScreenViewModel.gameGrid)
        gameScreenViewModel.numberOftry.value=3
        notifyDataSetChanged()
    }

    override fun onOverlapCardClicked(postion: Int , lost:Boolean) {
        if(gameScreenViewModel.numberOftry.value == 1 && lost){
            notifyLoss()
        }
            gameScreenViewModel.tryAgain()
            gameScreenViewModel.updateVisibility(postion)
    }

    private fun notifyLoss() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.lost_title)
        builder.setMessage(R.string.lost_msg)
        builder.setPositiveButton(R.string.retry){dialogInterface,which->
            reshuffle()
        }
        builder.setNegativeButton(R.string.reset){dialogInterface,which->
            resetGame()
        }
        builder.setNeutralButton(R.string.exit){dialogInterface,which->
            finish()
        }
        val lostAlertDialog =builder.create()
        lostAlertDialog.setCancelable(false)
        lostAlertDialog.show()
    }


    override fun notifyWin() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.win_title)
        builder.setMessage(R.string.win_msg)
        builder.setPositiveButton(R.string.play_again){dialogInterface,which->
            resetGame()
        }
        builder.setNegativeButton(R.string.exit){dialogInterface,which->
            finish()
        }
        val winAlertDialog = builder.create()
        winAlertDialog.setCancelable(false)
        winAlertDialog.show()
    }


}