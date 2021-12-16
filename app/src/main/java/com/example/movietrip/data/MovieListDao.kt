package com.example.movietrip.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movietrip.data.Movie

@Dao
interface MovieListDao{
    @Query("SELECT * FROM movie ORDER BY release_date DESC")
    fun getMovies(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("DELETE FROM movie")
    suspend fun deleteAllData()
}