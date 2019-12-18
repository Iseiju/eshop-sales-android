package com.example.switchsales.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.switchsales.activities.OnItemClick
import com.example.switchsales.interfaces.GetGameService
import com.example.switchsales.models.DataEnvelope
import com.example.switchsales.models.Game
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameViewModel(private val onItemClick: OnItemClick<Game>): ViewModel() {

    private val gameList = MutableLiveData<List<Game>>()

    val toastMessage = MutableLiveData<String>()

    init {
        getGames()
    }

    fun getList(): LiveData<List<Game>> {
        return gameList
    }

    fun getCount(): Int {
        return gameList.value?.size ?: 0
    }

    fun getBoxArt(position: Int): String {
        return gameList.value?.get(position)?.boxArt ?: ""
    }

    fun getGameName(position: Int): String {
        return gameList.value?.get(position)?.title ?: ""
    }

    fun getPrice(position: Int): String {
        return "$" + gameList.value?.get(position)?.price.toString()
    }

    fun getSalePrice(position: Int): String {
        return "$" + gameList.value?.get(position)?.salePrice.toString()
    }

    fun onClick(position: Int): () -> Unit = {
        gameList.value?.get(position)?.let { onItemClick.onClick(it) }
    }

    fun getGames() {
        val url = "https://switchsales.herokuapp.com"
//        val localhost =  "http://172.16.4.43:3000"
        val service = Retrofit
            .Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetGameService::class.java)

        val call = service.getGames()

        call.enqueue(object : Callback<DataEnvelope> {
            override fun onFailure(call: Call<DataEnvelope>, t: Throwable) {
                toastMessage.value = t.localizedMessage
            }

            override fun onResponse(call: Call<DataEnvelope>, response: Response<DataEnvelope>) {
                val data = response.body()?.data

                val sorted = data?.sortedBy {
                    it.title
                }

                gameList.value = sorted
            }
        })
    }
}