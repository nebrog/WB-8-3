package com.example.seventhweekapppt3.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.seventhweekapppt3.data.room.model.CatsFavEntity

@Dao
interface CatsFavDao {

    @Insert
    suspend fun insertFavCat(cat: CatsFavEntity)

    @Query("SELECT * FROM CatsFavEntity")
    fun getAllCats(): List<CatsFavEntity>


}