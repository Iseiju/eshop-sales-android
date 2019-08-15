package com.example.switchsales.interfaces

import com.example.switchsales.models.DataEnvelope
import retrofit2.Call
import retrofit2.http.GET

interface GetGameService {

    @GET("eshop-sales")
    fun getGames() : Call<DataEnvelope>
}