package com.example.demoapiapp.presentation.fragments.home_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapiapp.domain.model.MovieItem
import com.example.demoapiapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val products = MutableStateFlow<List<MovieItem>>(emptyList())

    init {
        getPopularMovies()
    }


    fun getPopularMovies() {
        viewModelScope.launch {
            val response = movieRepository.getPopularMovies()
            products.emit(response)
        }
    }
}