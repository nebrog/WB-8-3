package com.example.seventhweekapppt3.ui.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seventhweekapppt3.R
import com.example.seventhweekapppt3.ui.Repository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteCatsFragment : Fragment(R.layout.fragment_favourite_cats) {

    private val viewModel: FavouriteCatsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler_favourite)
        val adapter = FavouriteCatsAdapter()
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler.layoutManager = layoutManager
        recycler.adapter = adapter

        viewModel.catList.observe(viewLifecycleOwner) { catsList ->
            adapter.setData(catsList)
        }
    }
}