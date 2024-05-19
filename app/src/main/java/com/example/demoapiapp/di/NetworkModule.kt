package com.example.demoapiapp.di

import com.example.demoapiapp.data.remote.api.MovieApi
import com.example.demoapiapp.data.remote.repository.MovieRepositoryImpl
import com.example.demoapiapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun provideRetrofitClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(client : Retrofit) : MovieApi {
        return client.create(MovieApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(api : MovieApi) : MovieRepository {
        return MovieRepositoryImpl(api)
    }

}