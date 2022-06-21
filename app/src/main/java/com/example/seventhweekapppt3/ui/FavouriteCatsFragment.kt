package com.example.seventhweekapppt3.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seventhweekapppt3.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteCatsFragment : Fragment(R.layout.fragment_favourite_cats) {

    private val scope = CoroutineScope(Dispatchers.Main)
    @Inject
    lateinit var repository: Repository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler_favourite)
        val adapter = FavouriteCatsAdapter()
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
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