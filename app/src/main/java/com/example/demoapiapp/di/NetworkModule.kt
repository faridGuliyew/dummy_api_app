package com.example.demoapiapp.di

import com.example.demoapiapp.data.remote.api.MovieApi
import com.example.demoapiapp.data.remote.repository.AuthRepositoryImpl
import com.example.demoapiapp.data.remote.repository.MovieRepositoryImpl
import com.example.demoapiapp.domain.repository.AuthRepository
import com.example.demoapiapp.domain.repository.MovieRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
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

    @Provides
    @Singleton
    fun provideAuthRepository (firebaseAuth : FirebaseAuth) : AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth () : FirebaseAuth {
        return Firebase.auth
    }

}