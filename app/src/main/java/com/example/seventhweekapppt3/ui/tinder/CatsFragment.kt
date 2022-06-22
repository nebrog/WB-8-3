package com.example.seventhweekapppt3.ui.tinder

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.seventhweekapppt3.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatsFragment : Fragment(R.layout.fragment_cats) {

    private val viewModel: CatsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cat = view.findViewById<ImageView>(R.id.img_cat)
        val dislike = view.findViewById<ImageButton>(R.id.dislike)
        val favourites = view.findViewById<ImageButton>(R.id.favourites)
        val like = view.findViewById<ImageButton>(R.id.like)

        like.setOnClickListener {
            viewModel.saveLike()
        }
        dislike.setOnClickListener {
            viewModel.loadNewImage()
        }
        favourites.setOnClickListener {
            view.findNavController().navigate(R.id.action_catsFragment_to_favouriteCatsFragment)
        }

        viewModel.catLiveData.observe(viewLifecycleOwner) { catItem ->
            cat.setImageResource(R.drawable.ic_loading)
            if (catItem != null) {
                val uri = Uri.parse(catItem.url)
                cat.setImageURI(uri)
            }
        }
    }
}