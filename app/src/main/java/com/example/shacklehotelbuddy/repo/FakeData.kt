package com.example.shacklehotelbuddy.repo

import com.example.shacklehotelbuddy.model.Hotel
import com.example.shacklehotelbuddy.model.SearchQuery
import java.util.Date

val listOfHotels = listOf(
    Hotel(1, "The Ritz-Carlton", "New York City", "https://picsum.photos/500/300", 4.5, 500.0),
    Hotel(2, "The Peninsula", "San Francisco", "https://picsum.photos/500/300", 4.8, 450.0),
    Hotel(3, "The Four Seasons", "Los Angeles", "https://picsum.photos/500/300", 4.9, 400.0),
    Hotel(4, "The Mandarin Oriental", "Miami", "https://picsum.photos/500/300", 4.7, 350.0),
    Hotel(5, "The St. Regis", "Chicago", "https://picsum.photos/500/300", 4.6, 300.0),
    Hotel(6, "The Plaza", "New York City", "https://picsum.photos/500/300", 4.4, 250.0),
    Hotel(7, "The Carlyle", "New York City", "https://picsum.photos/500/300", 4.9, 200.0),
    Hotel(8, "The Langham", "London", "https://picsum.photos/500/300", 4.8, 150.0),
    Hotel(9, "The Ritz-Carlton", "Paris", "https://picsum.photos/500/300", 4.7, 100.0),
    Hotel(10, "The Peninsula", "Tokyo", "https://picsum.photos/500/300", 4.6, 50.0)
)


val fakeSearchQuires = listOf(
    SearchQuery(
        id = 1,
        checkInDate = Date(),
        checkoutDate = Date(),
        adultsCount = 2,
        childrenCount = 0
    ),
    SearchQuery(
        id = 2,
        checkInDate = Date(),
        checkoutDate = Date(),
        adultsCount = 1,
        childrenCount = 1
    ),
    SearchQuery(
        id = 3,
        checkInDate = Date(),
        checkoutDate = Date(),
        adultsCount = 3,
        childrenCount = 2
    ),
    SearchQuery(
        id = 4,
        checkInDate = Date(),
        checkoutDate = Date(),
        adultsCount = 2,
        childrenCount = 1
    ),
    SearchQuery(
        id = 5,
        checkInDate = Date(),
        checkoutDate = Date(),
        adultsCount = 4,
        childrenCount = 0
    )
)
