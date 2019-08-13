package com.example.switchsales.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.switchsales.R
import com.example.switchsales.models.Game
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_game_info.*
import java.text.SimpleDateFormat

class GameInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_info)

        initViews()
    }

    private fun initViews() {
        val game = intent.getParcelableExtra<Game>("game") as Game

        setSupportActionBar(findViewById(R.id.gameInfoToolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        eshopButton.setOnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse(game.url)
            startActivity(openUrl)
        }

        Picasso.get().load(game.boxArt).placeholder(R.drawable.ic_placeholder).into(boxArtImageView)
        gameNameLabel.text = game.title
        salePriceLabel.text = "$" + game.salePrice.toString()
        priceLabel.text = "$" + game.price.toString()
        descriptionLabel.text = game.description

        val releaseDate = game.releaseDate.split("T").first()
        val parser = SimpleDateFormat("yyyy-mm-dd")
        val formatter = SimpleDateFormat("MMM d, yyyy")
        val releaseDateFormatted = formatter.format(parser.parse(releaseDate))

        releaseDateLabel.text = "Release Date: " + releaseDateFormatted
        categoryLabel.text = "Category: " + game.category.first()
        companyLabel.text = "Company: " + game.company.first()

        if (game.esrb == "Everyone") {
            ratingImageView.setImageResource(R.mipmap.ic_everyone)
        } else if (game.esrb == "Teen") {
            ratingImageView.setImageResource(R.mipmap.ic_teen)
        } else if (game.esrb == "Mature") {
            ratingImageView.setImageResource(R.mipmap.ic_mature17plus)
        } else if (game.esrb == "Everyone 10+") {
            ratingImageView.setImageResource(R.mipmap.ic_everyone10plus)
        } else if (game.esrb == "Adults Only") {
            ratingImageView.setImageResource(R.mipmap.ic_adultsonly)
        } else if (game.esrb == "Early Childhood") {
            ratingImageView.setImageResource(R.mipmap.ic_earlychildhood)
        } else {
            ratingImageView.setImageResource(R.mipmap.ic_ratingpending)
        }
    }
}
