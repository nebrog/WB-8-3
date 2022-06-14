package com.example.seventhweekapppt3.data.room

import android.util.Log
import com.example.seventhweekapppt3.data.room.model.CatsFavEntity
import com.example.seventhweekapppt3.ui.Repository
import com.example.seventhweekapppt3.ui.models.CatItem
import io.ktor.http.parsing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.sql.SQLException
import java.util.*

class RoomRepository(
    private val db: CatsFavDatabase,

    ) : Repository {
    override suspend fun getNewImage(): CatItem? {
        return null
    }

    override suspend fun saveFavCats(cat: CatItem) {
        safeRoomCall {
            val catsFavEntity = CatsFavEntity(cat.imageId, cat.url)
            db.catsFavDao().insertFavCat(catsFavEntity)
        }
    }

    override suspend fun getFavCats(): List<CatItem>? {
        return safeRoomCall {
            val cats = db.catsFavDao().getAllCats()
            val catItemList = ArrayList<CatItem>()
            for (i in 0 until cats.size) {
                val imageId = cats[i].image_id
                val url = cats[i].url
                catItemList.add(CatItem(imageId, url))
            }
            catItemList
        }
    }

    private suspend fun <R> safeRoomCall(action: suspend () -> R): R? {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext action()

            } catch (e: SQLException) {
                Log.e("Nebrog", e.message.toString())
                return@withContext null
            } catch (e: IOException) {
                Log.e("Nebrog", e.message.toString())
                return@withContext null
            }
        }
    }
}
