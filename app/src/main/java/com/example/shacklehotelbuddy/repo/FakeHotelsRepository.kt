package com.example.shacklehotelbuddy.repo

import com.example.shacklehotelbuddy.model.Hotel
import com.example.shacklehotelbuddy.model.SearchQuery
import com.example.shacklehotelbuddy.network.ApiService
import com.example.shacklehotelbuddy.room.SearchHistoryDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FakeHotelsRepository(
    private val apiService: ApiService,
    private val searchHistoryDao: SearchHistoryDao
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    suspend fun getHotelsList(searchQuery: SearchQuery): List<Hotel> {
//        return apiService.getHotelsList()
        return listOfHotels
    }

    fun addSearchQuery(searchQuery: SearchQuery) {
        coroutineScope.launch(Dispatchers.IO) {
            searchHistoryDao.addSearchQuery(searchQuery)
        }

    }

    suspend fun getSearchHistory(): List<SearchQuery> {
        return withContext(Dispatchers.IO) {
            searchHistoryDao.getSearchHistory()
        }
    }
}