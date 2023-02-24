package com.example.multimodulecleanarchitectureapp

import com.example.domain.model.CategoryResponse

sealed class MainState {

    object Idle: MainState()

    object Loading : MainState()

    data class Meals(val meals: CategoryResponse) : MainState()

    data class Error(val message: String) : MainState()
}