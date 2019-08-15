package com.example.switchsales.interfaces

import com.example.switchsales.models.DataEnvelope
import retrofit2.Call
import retrofit2.http.GET

interface GetGameService {

    @GET("games/eshop-sales")
    fun getGames() : Call<DataEnvelope>
}