package com.example.movietrip.data

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.*

class DbTypeConverters {
    @TypeConverter
    fun fromTimestamp(value:Long?):Date?{
        return if(value==null)null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date:Date?):Long?{
        return date?.time
    }
}