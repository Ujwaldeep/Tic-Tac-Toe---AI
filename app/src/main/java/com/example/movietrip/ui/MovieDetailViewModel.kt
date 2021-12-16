package com.example.movietrip.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movietrip.data.Movie
import com.example.movietrip.data.MovieDetailRepository
import com.example.movietrip.data.MovieListRepository

class MovieDetailViewModel(id: Long, application: Application): ViewModel() {
    private val repo: MovieDetailRepository = MovieDetailRepository(application)
    val movie: LiveData<Movie> = repo.getMovie(id)
}