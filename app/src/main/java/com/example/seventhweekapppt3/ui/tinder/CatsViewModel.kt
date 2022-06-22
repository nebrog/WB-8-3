package com.example.seventhweekapppt3.ui.tinder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seventhweekapppt3.ui.Repository
import com.example.seventhweekapppt3.ui.models.CatItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    val catLiveData = MutableLiveData<CatItem?>()

    init {
        loadNewImage()
    }

    fun loadNewImage() {
        viewModelScope.launch {
            catLiveData.value = null
            catLiveData.value = repository.getNewImage()
        }
    }

    fun saveLike() {
        val cat = catLiveData.value
        if (cat != null) {
            viewModelScope.launch {
                repository.saveFavCats(cat)
            }
            loadNewImage()
        }
    }
}