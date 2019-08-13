package com.example.switchsales.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.switchsales.R
import com.example.switchsales.activities.GameInfoActivity
import com.example.switchsales.models.Game
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.game_row.view.*

class MainAdapter(val context: Context, private val game: List<Game>): RecyclerView.Adapter<GameViewHolder>() {

    override fun getItemCount(): Int {
        return game.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.game_row, parent, false)

        return GameViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val boxArtView = holder.view.boxArtImageView
        Picasso
            .get()
            .load(game[position].boxArt)
            .placeholder(R.drawable.ic_placeholder)
            .into(boxArtView)

        holder.view.gameNameLabel.text = game[position].title
        holder.view.priceLabel.text = "$" + game[position].price.toString()
        holder.view.salePriceLabel.text = "$" + game[position].salePrice.toString()

        holder.view.setOnClickListener {
            val intent = Intent(context, GameInfoActivity::class.java)
            intent.putExtra("game", game[position])
            context.startActivity(intent)
        }
    }
}

class GameViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}