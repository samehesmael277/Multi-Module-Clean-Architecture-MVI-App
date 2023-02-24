package com.example.data.repo

import com.example.data.mapper.MealsMapper.toCategoryResponse
import com.example.data.remote.ApiService
import com.example.domain.model.CategoryResponse
import com.example.domain.repo.IMealsRepo

class RepoImp(private val apiService: ApiService) : IMealsRepo {

    override suspend fun getMealsFromApi(): CategoryResponse =
        apiService.getMeals().toCategoryResponse()
}