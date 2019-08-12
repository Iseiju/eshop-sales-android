package com.example.switchsales.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.switchsales.R
import com.example.switchsales.models.DataEnvelope
import com.example.switchsales.models.Game
import kotlinx.android.synthetic.main.game_row.view.*

class MainAdapter(private val game: List<Game>): RecyclerView.Adapter<GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.game_row, parent, false)

        return GameViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.view.gameNameLabel.text = game[position].title
        holder.view.priceLabel.text = game[position].price.toString()
        holder.view.salePriceLabel.text = game[position].salePrice.toString()
    }

    override fun getItemCount(): Int {
        return game.count()
    }
}

class GameViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}