package com.example.seventhweekapppt3.ui

import com.example.seventhweekapppt3.ui.models.CatItem

interface Repository {
    suspend fun getFavCats(): List<CatItem>?
    suspend fun saveFavCats(cat: CatItem)
    suspend fun getNewImage(): CatItem?
}