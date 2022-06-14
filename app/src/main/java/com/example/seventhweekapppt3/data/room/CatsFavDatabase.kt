package com.example.seventhweekapppt3.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.seventhweekapppt3.data.room.model.CatsFavEntity

@Database(entities = [CatsFavEntity::class], version = 1)
abstract class CatsFavDatabase: RoomDatabase() {
    abstract fun catsFavDao(): CatsFavDao
}