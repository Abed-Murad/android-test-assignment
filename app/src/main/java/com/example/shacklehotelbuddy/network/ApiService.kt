package com.example.shacklehotelbuddy.network

import retrofit2.http.POST

interface ApiService {
    @POST("/properties/v2/list")
    suspend fun getHotelsList(): String
}
