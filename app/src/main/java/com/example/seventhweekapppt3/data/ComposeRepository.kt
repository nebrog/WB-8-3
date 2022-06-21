package com.example.seventhweekapppt3.data

import com.example.seventhweekapppt3.data.network.NetworkRepository
import com.example.seventhweekapppt3.data.room.RoomRepository
import com.example.seventhweekapppt3.ui.Repository
import com.example.seventhweekapppt3.ui.models.CatItem
import javax.inject.Inject

class ComposeRepository @Inject constructor(
    private val roomRepository: RoomRepository,
    private val networkRepository: NetworkRepository
) : Repository {
    override suspend fun getFavCats(): List<CatItem>? {
        val cats = roomRepository.getFavCats()
        if (cats == null) {
            val networkCats = networkRepository.getFavCats()
            return networkCats
        } else {
            return cats
        }
    }

    override suspend fun saveFavCats(cat: CatItem) {
        networkRepository.saveFavCats(cat)
        roomRepository.saveFavCats(cat)
    }

    override suspend fun getNewImage(): CatItem? {
        return networkRepository.getNewImage()
    }
}