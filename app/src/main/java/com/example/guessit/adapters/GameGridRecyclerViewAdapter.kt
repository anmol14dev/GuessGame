package com.example.guessit.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guessit.model.GameItemModel
import com.example.guessit.databinding.SingleCardItemBinding
import com.example.guessit.listener.RecycleViewClickListener

class GameGridRecyclerViewAdapter(
    var grid: List<GameItemModel>,
    val listener: RecycleViewClickListener
) :
    RecyclerView.Adapter<GameGridRecyclerViewAdapter.GameGridRecyclerViewHolder>() {

    inner class GameGridRecyclerViewHolder(val cardItemBinding: SingleCardItemBinding) :
        RecyclerView.ViewHolder(cardItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameGridRecyclerViewHolder {
        val binding =
            SingleCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameGridRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameGridRecyclerViewHolder, position: Int) {
        val singleItem = grid.get(position)
        holder.cardItemBinding.gridItem = singleItem
        holder.cardItemBinding.executePendingBindings()
        holder.cardItemBinding.cardOverlap.setOnClickListener {
            it.visibility = View.GONE
            holder.cardItemBinding.cardValue.visibility=View.VISIBLE
            if(grid.get(position).isCorrect){
                listener.notifyWin()
                listener.onOverlapCardClicked(position,false)
            }
            else {
                listener.onOverlapCardClicked(position, true)
            }
        }

    }

    override fun getItemCount(): Int {
        return grid.size
    }
}