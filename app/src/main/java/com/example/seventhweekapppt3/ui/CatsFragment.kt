package com.example.seventhweekapppt3.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.seventhweekapppt3.R
import com.example.seventhweekapppt3.ui.models.CatItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CatsFragment : Fragment(R.layout.fragment_cats) {

    private val scope = CoroutineScope(Dispatchers.Main)
    private var cat: ImageView? = null
    private var item: CatItem? = null

    @Inject
    lateinit var repository: Repository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cat = view.findViewById<ImageView>(R.id.img_cat)
        val dislike = view.findViewById<ImageButton>(R.id.dislike)
        val favourites = view.findViewById<ImageButton>(R.id.favourites)
        val like = view.findViewById<ImageButton>(R.id.like)
        dislike.setOnClickListener { loadNewImage() }
        like.setOnClickListener { saveLike(item) }
        loadNewImage()

        favourites.setOnClickListener {
            view.findNavController().navigate(R.id.action_catsFragment_to_favouriteCatsFragment)
        }
    }
    private fun loadNewImage() {
        scope.launch {
            item = null
            cat?.setImageResource(R.drawable.ic_loading)
            item = repository.getNewImage()
            if (item != null) {
                val uri = Uri.parse(item?.url)
                cat?.setImageURI(uri)
            }
        }
    }

    private fun saveLike(item: CatItem?) {
        scope.launch {
            if (item != null) {
                loadNewImage()
                repository.saveFavCats(item)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}