package com.example.shacklehotelbuddy.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shacklehotelbuddy.model.SearchQuery

@Dao
interface SearchHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSearchQuery(searchQuery: SearchQuery)
    @Query("SELECT * FROM search_query")
    fun getSearchHistory(): List<SearchQuery>

}