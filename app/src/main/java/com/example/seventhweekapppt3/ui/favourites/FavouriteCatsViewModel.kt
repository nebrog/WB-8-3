package com.example.seventhweekapppt3.ui.favourites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seventhweekapppt3.ui.Repository
import com.example.seventhweekapppt3.ui.models.CatItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteCatsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val catList = MutableLiveData<List<CatItem>>()

    init {
        viewModelScope.launch {
            var cats: List<CatItem>? = null
            while (cats == null) {
                cats = repository.getFavCats()?.reversed()
                delay(1000)
            }
            catList.value = cats
        }
    }
}
