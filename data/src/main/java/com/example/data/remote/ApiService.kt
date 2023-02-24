package com.example.data.remote

import com.example.data.model.CategoryResponseDto
import retrofit2.http.GET

interface ApiService {

    @GET("categories.php")
    suspend fun getMeals(): CategoryResponseDto
}