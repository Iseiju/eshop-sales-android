package com.example.switchsales.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.switchsales.R
import com.example.switchsales.viewmodels.GameViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.game_row.view.*

class MainAdapter(private val viewModel: GameViewModel): RecyclerView.Adapter<GameViewHolder>() {

    override fun getItemCount(): Int {
        return viewModel.getCount()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.game_row, parent, false)

        return GameViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(viewModel.getBoxArt(position),
                    viewModel.getGameName(position),
                    viewModel.getPrice(position),
                    viewModel.getSalePrice(position),
                    viewModel.onClick(position))
    }
}

class GameViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bind(url: String, gameName: String, price: String, salePrice: String, onClick: () -> Unit) {
        Picasso
            .get()
            .load(url)
            .placeholder(R.drawable.ic_placeholder)
            .into(itemView.boxArtImageView)

        itemView.gameNameLabel.text = gameName
        itemView.priceLabel.text = price
        itemView.salePriceLabel.text = salePrice
        itemView.setOnClickListener { onClick() }
    }
}