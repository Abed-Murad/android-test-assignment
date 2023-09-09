package com.example.shacklehotelbuddy.featurs.home

import androidx.lifecycle.ViewModel
import com.example.shacklehotelbuddy.model.SearchQuery
import com.example.shacklehotelbuddy.repo.FakeHotelsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date
import java.util.Random
import javax.inject.Inject



data class MyState(val checkInDate: Date = Date(),
                   val checkoutDate: Date = Date(),
                   val adultsCount:Int = 1,
                   val childrenCount:Int = 0,
                    val searchHistory: List<SearchQuery> = emptyList())

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: FakeHotelsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(MyState())
    val uiState = _uiState.asStateFlow()


    fun getSearchQuery() = SearchQuery(
        id = Random().nextInt(),
        checkInDate =  uiState.value.checkInDate,
        checkoutDate = uiState.value.checkoutDate,
        adultsCount = uiState.value.adultsCount,
        childrenCount = uiState.value.childrenCount )

    fun updateCheckInDate(checkInDate: Date) {
        _uiState.update { currentState ->
            currentState.copy(
                checkInDate = checkInDate
            )
        }
    }
    fun updateCheckoutDate(checkoutDate: Date) {
        _uiState.update { currentState ->
            currentState.copy(
                checkoutDate = checkoutDate
            )
        }
    }

    fun updateAdultsCount(newAdultsCount: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                adultsCount = newAdultsCount
            )
        }
    }

    fun updateChildrenCount(newChildrenCount: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                childrenCount = newChildrenCount
            )
        }
    }

    fun addSearchQuery(searchQuery: SearchQuery) {
        repository.addSearchQuery(searchQuery)
    }

    suspend fun getSearchHistory() {
        _uiState.update { currentState ->
            currentState.copy(
                searchHistory = repository.getSearchHistory()
            )
        }
    }

}