package com.example.demoapiapp.data.remote.repository

import com.example.demoapiapp.data.remote.api.MovieApi
import com.example.demoapiapp.domain.model.MovieItem
import com.example.demoapiapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
) : MovieRepository {
    override suspend fun getPopularMovies(): List<MovieItem> {

        val response = api.getPopularMovies()
        return response.results.map {
            MovieItem(
                image = it.posterPath,
                title = it.title,
                description = it.overview
            )
        }
    }

}