package com.example.starwars.di

import android.content.Context
import androidx.room.Room
import com.example.starwars.data.local.StarWarsLocalDB
import com.example.starwars.data.local.dao.FavoriteDao
import com.example.starwars.data.network.SimpleRetro
import com.example.starwars.domain.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    fun baseUrl() = BASE_URL

    @Provides
    fun logging() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder().addInterceptor(logging()).build()

    @Provides
    fun gson(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetro(): SimpleRetro =
        Retrofit.Builder()
            .baseUrl(baseUrl())
            .addConverterFactory(gson())
            .client(okHttpClient())
            .build()
            .create(SimpleRetro::class.java)

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, StarWarsLocalDB::class.java, "starwars_room").build()

    @Provides
    fun provideStarWarsDao(appDataBaseNews: StarWarsLocalDB): FavoriteDao {
        return appDataBaseNews.favoritePerson()
    }
}