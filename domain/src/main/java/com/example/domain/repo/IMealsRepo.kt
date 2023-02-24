package com.example.domain.repo

import com.example.domain.model.CategoryResponse

interface IMealsRepo {

    suspend fun getMealsFromApi(): CategoryResponse
}