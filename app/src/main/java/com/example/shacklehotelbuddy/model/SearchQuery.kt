package com.example.shacklehotelbuddy.model

import android.net.Uri
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "search_query", primaryKeys = ["id", "checkInDate", "checkoutDate"])
data class SearchQuery(

    var id: Int = 0,

    @TypeConverters(DateTypeConverter::class)
    val checkInDate: Date,

    @TypeConverters(DateTypeConverter::class)
    val checkoutDate: Date,

    val adultsCount: Int,

    val childrenCount: Int

) : Parcelable{
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}


class DateTypeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
