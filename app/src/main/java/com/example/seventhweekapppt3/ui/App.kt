package com.example.seventhweekapppt3.ui

import android.app.Application
import androidx.room.Room
import com.example.seventhweekapppt3.data.ComposeRepository
import com.example.seventhweekapppt3.data.network.NetworkRepository
import com.example.seventhweekapppt3.data.room.CatsFavDatabase
import com.example.seventhweekapppt3.data.room.RoomRepository
import com.facebook.drawee.backends.pipeline.Fresco
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class App : Application() {

    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        val db = Room.databaseBuilder(
            applicationContext,
            CatsFavDatabase::class.java, "cats_database"
        ).build()
        val client: HttpClient = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
        val roomRepository = RoomRepository(db)
        val networkRepository = NetworkRepository(client)
        repository = ComposeRepository(roomRepository, networkRepository)
    }
}