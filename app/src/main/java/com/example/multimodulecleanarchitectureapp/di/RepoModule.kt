package com.example.multimodulecleanarchitectureapp.di

import com.example.data.remote.ApiService
import com.example.data.repo.RepoImp
import com.example.domain.repo.IMealsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepo(apiService: ApiService): IMealsRepo {
        return RepoImp(apiService)
    }
}