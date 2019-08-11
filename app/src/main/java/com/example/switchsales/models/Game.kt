package com.example.switchsales.models

import com.google.gson.annotations.SerializedName

data class Game(val url: String,
                val title: String,
                val description: String,
                val boxArt: String,
                val releaseDate: String,
                @SerializedName("categories") val category: List<String>,
                val esrb: String,
                val company: List<String>,
                val availability: List<String>,
                val price: Double,
                val salePrice: Double) {
}