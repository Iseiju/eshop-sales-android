package com.example.switchsales.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.HtmlCompat
import com.example.switchsales.R
import com.example.switchsales.extensions.fetchViewModel
import com.example.switchsales.models.Game
import com.example.switchsales.viewmodels.GameInfoViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_game_info.*
import java.text.SimpleDateFormat

class GameInfoActivity : AppCompatActivity() {

    companion object {
        private const val gameKey = "game"

        fun getIntent(context: Context, model: Game): Intent {
            val intent = Intent(context, GameInfoActivity::class.java)
            intent.putExtra(gameKey, model)
            return intent
        }
    }

    private val viewModel by lazy {
        fetchViewModel { GameInfoViewModel(intent.getParcelableExtra(gameKey) as Game) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_info)

        initViews()
    }

    private fun initViews() {
        setSupportActionBar(findViewById(R.id.gameInfoToolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        eshopButton.setOnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse(viewModel.getGameUrl())
            startActivity(openUrl)
        }

        Picasso
            .get()
            .load(viewModel.getBoxArt())
            .placeholder(R.drawable.ic_placeholder)
            .into(boxArtImageView)

        gameNameLabel.text = viewModel.getGameTitle()
        salePriceLabel.text = viewModel.getSalePrice()
        priceLabel.text = viewModel.getPrice()
        descriptionLabel.text = viewModel.getDescription()
        releaseDateLabel.text = viewModel.getReleaseDate()
        categoryLabel.text = viewModel.getCategory()
        companyLabel.text = viewModel.getCompany()

        ratingImageView.setImageResource(viewModel.setESRBImage())
    }
}
