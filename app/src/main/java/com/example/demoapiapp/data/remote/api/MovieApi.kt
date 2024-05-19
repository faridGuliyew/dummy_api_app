package com.example.demoapiapp.data.remote.api

import com.example.demoapiapp.data.remote.model.PopularMoviesResponse
import retrofit2.http.GET

interface MovieApi {

    @GET("movie/popular?api_key=fa22bdcd3f461d2aec53c6b2b47e85e7&page=1&name=Farid")
    suspend fun getPopularMovies(): PopularMoviesResponse

}