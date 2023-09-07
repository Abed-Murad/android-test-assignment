package com.example.shacklehotelbuddy.featurs.details

import androidx.lifecycle.ViewModel
import com.example.shacklehotelbuddy.model.Hotel
import com.example.shacklehotelbuddy.model.SearchQuery
import com.example.shacklehotelbuddy.repo.FakeHotelsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
open class SearchResultsViewModel @Inject constructor(private val fakeHotelsRepository: FakeHotelsRepository) : ViewModel() {

    private var _hotels = MutableStateFlow(emptyList<Hotel>())
    val hotels: StateFlow<List<Hotel>> = _hotels

    suspend fun getHotelsList(searchQuery: SearchQuery) {
        _hotels.value = fakeHotelsRepository.getHotelsList(searchQuery)
    }
}