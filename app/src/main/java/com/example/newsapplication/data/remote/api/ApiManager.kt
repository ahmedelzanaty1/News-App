package com.example.newsapplication.data.remote.api

import com.example.newsapplication.conestant.Constants
import com.example.newsapplication.data.remote.dto.ArticleResponse
import com.example.newsapplication.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiManager {
    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("category") categoryId: String

    ): NewsResponse

    @GET("everything")
    suspend fun getArticles(
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String,
    ) : ArticleResponse

}