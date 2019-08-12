package com.example.switchsales.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.switchsales.adapters.MainAdapter
import com.example.switchsales.R
import com.example.switchsales.interfaces.GetGameService
import com.example.switchsales.models.DataEnvelope
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        getGameList()
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getGameList() {
        val url = "https://switchsales.herokuapp.com/"
        val service = Retrofit
            .Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetGameService::class.java)

        val call = service.getGames()
        call.enqueue(object : Callback<DataEnvelope> {
            override fun onFailure(call: Call<DataEnvelope>, t: Throwable) {
                Toast.makeText(applicationContext, "Error reading JSON", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<DataEnvelope>, response: Response<DataEnvelope>) {
                val data = response.body()?.data

                runOnUiThread {
                    recyclerView.adapter = data?.let { MainAdapter(it) }
                }
            }
        })
    }
}
