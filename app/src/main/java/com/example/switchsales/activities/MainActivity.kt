package com.example.switchsales.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.switchsales.adapters.MainAdapter
import com.example.switchsales.R
import com.example.switchsales.extensions.fetchViewModel
import com.example.switchsales.models.Game
import com.example.switchsales.viewmodels.GameViewModel
import kotlinx.android.synthetic.main.activity_main.*

interface OnItemClick<T> {

    fun onClick(model: T)
}

class MainActivity : AppCompatActivity(), OnItemClick<Game> {

    private val viewModel by lazy {
        fetchViewModel { GameViewModel(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getList().observe(this, Observer { games: List<Game>? ->
            recyclerView.adapter = games?.let { MainAdapter(viewModel) }
            progressBar.isVisible = false
            swipeRefreshLayout.isRefreshing = false
        })

        viewModel.toastMessage.observe(this, Observer { message: String ->
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
        })

        setSupportActionBar(findViewById(R.id.mainToolbar))

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getGames()
        }
    }

    override fun onClick(model: Game) {
        GameInfoActivity.getIntent(this, model).apply {
            this@MainActivity.startActivity(this)
        }
    }
}