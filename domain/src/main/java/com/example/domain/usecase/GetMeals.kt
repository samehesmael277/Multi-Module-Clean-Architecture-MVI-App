package com.example.domain.usecase

import com.example.domain.repo.IMealsRepo

class GetMeals(private val IMealsRepo: IMealsRepo) {

    suspend operator fun invoke() = IMealsRepo.getMealsFromApi()
}