package com.example.seventhweekapppt3.di

import android.content.Context
import androidx.room.Room
import com.example.seventhweekapppt3.data.ComposeRepository
import com.example.seventhweekapppt3.data.room.CatsFavDatabase
import com.example.seventhweekapppt3.ui.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun clientHttp(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    @Provides
    fun roomProvide(
        @ApplicationContext
        applicationContext: Context
    ): CatsFavDatabase {
        return Room.databaseBuilder(
            applicationContext,
            CatsFavDatabase::class.java, "cats_database"
        ).build()
    }

    @Provides
    fun provideRepository(repository: ComposeRepository):Repository{
        return repository
    }

}