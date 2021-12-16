package com.example.movietrip.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.movietrip.data.Movie

@Dao
interface MovieDetailDao {
    @Query("SELECT * FROM movie WHERE `id`==:id")
    fun getMovie(id:Long):LiveData<Movie>
}