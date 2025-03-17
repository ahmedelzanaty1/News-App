package com.example.newsapplication.di

import com.example.newsapplication.conestant.Constants
import com.example.newsapplication.data.remote.api.ApiManager
import com.example.newsapplication.data.repository.NewsRepositoryImpl
import com.example.newsapplication.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit (): ApiManager {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiManager::class.java)
    }
    @Provides
    @Singleton
    fun provideRepository(apiManager: ApiManager): NewsRepository {
        return NewsRepositoryImpl(apiManager)

    }
}