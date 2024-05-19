package com.example.demoapiapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoapiapp.databinding.ItemMovieBinding
import com.example.demoapiapp.domain.model.MovieItem

class PopularMoviesAdapter : RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {
    class PopularMoviesViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(
            binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    var data: List<MovieItem> = emptyList()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val movie = data[position]
        with(holder.binding) {
            textMovieName.text = movie.title
            textMovieDesc.text = movie.description
            Glide.with(imageViewPoster).load(
                "https://image.tmdb.org/t/p/w500" + movie.image
            ).into(imageViewPoster)
        }
    }

    fun updateAdapter(response: List<MovieItem>) {
        data = response
        notifyDataSetChanged()
    }

}