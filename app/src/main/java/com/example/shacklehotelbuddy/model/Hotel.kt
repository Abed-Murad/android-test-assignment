package com.example.shacklehotelbuddy.model

data class Hotel(
    val id: Int,
    val name: String,
    val location: String,
    val imageUrl: String,
    val ratting: Double,
    val pricePerNight: Double
)
