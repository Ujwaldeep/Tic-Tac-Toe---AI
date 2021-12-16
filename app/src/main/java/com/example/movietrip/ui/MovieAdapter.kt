package com.example.movietrip.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietrip.R
import com.example.movietrip.data.Movie
import com.example.movietrip.data.network.TmdbService
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.list_item.*


@Suppress("DEPRECATION")
class MovieAdapter(private val listener:(Long)->Unit):
    ListAdapter<Movie, MovieAdapter.ViewHolder>(DiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class ViewHolder (override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        init{
            itemView.setOnClickListener{
                listener.invoke(getItem(adapterPosition).id)
            }
        }

        fun bind(movie: Movie){
            with(movie){
                Glide.with(containerView)
                    .load(TmdbService.POSTER_BASE_URL+movie.posterPath)
                    .error(R.drawable.poster_placeholder)
                    .into(poster_movie)
                title_movie.text = movie.title
            }

        }
        }
}
    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }