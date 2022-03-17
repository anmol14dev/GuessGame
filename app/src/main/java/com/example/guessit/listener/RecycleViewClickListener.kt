package com.example.guessit.listener

interface RecycleViewClickListener {
    fun onOverlapCardClicked(postion:Int , lost :Boolean)
    fun notifyWin()

}