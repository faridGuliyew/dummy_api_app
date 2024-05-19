package com.example.demoapiapp.domain.repository

import com.example.demoapiapp.domain.model.MovieItem

interface MovieRepository {

    suspend fun getPopularMovies(): List<MovieItem>

}