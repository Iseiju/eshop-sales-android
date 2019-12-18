package com.example.switchsales.viewmodels

import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModel
import com.example.switchsales.R
import com.example.switchsales.models.Game
import java.text.SimpleDateFormat

class GameInfoViewModel(private val game: Game): ViewModel() {

    fun getGameUrl(): String {
        return game.url
    }

    fun getBoxArt(): String {
        return game.boxArt
    }

    fun getGameTitle(): String {
        return game.title
    }

    fun getPrice(): String {
        return "$" + game.price.toString()
    }

    fun getSalePrice(): String {
        return "$" + game.salePrice.toString()
    }

    fun getDescription(): Spanned {
        return HtmlCompat.fromHtml(game.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    fun getReleaseDate(): String {
        val releaseDate = game.releaseDate.split("T").first()
        val parser = SimpleDateFormat("yyyy-mm-dd")
        val formatter = SimpleDateFormat("MMM d, yyyy")

        return "Release Date: " + formatter.format(parser.parse(releaseDate))
    }

    fun getCategory(): String {
        return "Category: " + game.category.first()
    }

    fun getCompany(): String {
        return "Company: " + game.company.first()
    }

    fun setESRBImage(): Int {
        when (game.esrb) {
            "Everyone" -> return R.mipmap.ic_everyone
            "Teen" -> return R.mipmap.ic_teen
            "Mature" -> return R.mipmap.ic_mature17plus
            "Everyone 10+" -> R.mipmap.ic_everyone10plus
            "Adults Only" -> R.mipmap.ic_adultsonly
            "Early Childhood" -> R.mipmap.ic_earlychildhood
        }

        return R.mipmap.ic_ratingpending
    }
}