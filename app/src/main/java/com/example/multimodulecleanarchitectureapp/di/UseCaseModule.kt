package com.example.multimodulecleanarchitectureapp.di

import com.example.domain.repo.IMealsRepo
import com.example.domain.usecase.GetMeals
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideUseCase(mealsRepo: IMealsRepo): GetMeals {
        return GetMeals(mealsRepo)
    }
}