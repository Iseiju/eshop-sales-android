package com.example.switchsales.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.switchsales.R
import kotlinx.android.synthetic.main.game_row.view.*

class MainAdapter: RecyclerView.Adapter<GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.game_row, parent, false)

        return GameViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.view.gameName.text = "asd"
    }

    override fun getItemCount(): Int {
        return 10
    }
}

class GameViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}