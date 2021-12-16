package com.example.movietrip.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.movietrip.R
import com.example.movietrip.data.Movie
import com.example.movietrip.data.network.TmdbService
import com.example.movietrip.readableFormat
import kotlinx.android.synthetic.main.fragment_movie_detail.*


@Suppress("DEPRECATION")
class MovieDetail : Fragment() {
   private lateinit var viewModel: MovieDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val id: Long = MovieDetailArgs.fromBundle(requireArguments()).id
        val viewModelFactory = MovieDetailViewModelFactory(id,requireActivity().application)

        viewModel = ViewModelProviders.of(this,viewModelFactory).get(MovieDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.movie.observe(viewLifecycleOwner, Observer {
            setData(it)
        })
    }

    private fun setData(movie: Movie) {
        movie_title.text = movie.title
        movie_overview.text = movie.overView
        movie_release_date.text = movie.releaseDate.readableFormat()
        Glide.with(requireActivity())
            .load(TmdbService.POSTER_BASE_URL+movie.posterPath)
            .error(R.drawable.poster_placeholder)
            .into(movie_poster)
        Glide.with(requireActivity())
            .load(TmdbService.BACKDROP_BASE_URL + movie.backdropPath)
            .error(R.drawable.poster_placeholder)
            .into(movie_backdrop)
    }
}