package com.example.movietrip.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey
    val id:Long,

    val title:String,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String,

    @ColumnInfo(name ="backdrop_path")
    @SerializedName("backdrop_path")
    val backdropPath: String,


    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overView: String,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate: Date

)
