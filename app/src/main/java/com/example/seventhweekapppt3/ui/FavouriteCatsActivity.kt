package com.example.seventhweekapppt3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seventhweekapppt3.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class FavouriteCatsActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Dispatchers.Main)
    private val repository by lazy { (applicationContext as App).repository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_cats)
        getSupportActionBar()?.hide()
        val recycler = findViewById<RecyclerView>(R.id.recycler_favourite)
        val adapter = FavouriteCatsAdapter()
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler.layoutManager = layoutManager
        recycler.adapter = adapter
        scope.launch {
            adapter.setData(repository.getFavCats()?.reversed())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}