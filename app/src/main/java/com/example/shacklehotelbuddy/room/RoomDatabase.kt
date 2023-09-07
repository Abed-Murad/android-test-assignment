package com.example.shacklehotelbuddy.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shacklehotelbuddy.model.DateTypeConverter
import com.example.shacklehotelbuddy.model.SearchQuery

@Database(entities = [(SearchQuery::class)], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class SearchHistoryDatabase : RoomDatabase() {
    abstract fun searchQueryDao(): SearchHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: SearchHistoryDatabase? = null
        fun getInstance(context: Context): SearchHistoryDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SearchHistoryDatabase::class.java,
                        "search_query_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}