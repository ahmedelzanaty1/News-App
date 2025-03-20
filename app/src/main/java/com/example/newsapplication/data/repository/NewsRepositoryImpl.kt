package com.example.newsapplication.data.repository

import com.example.newsapplication.conestant.Constants
import com.example.newsapplication.data.remote.api.ApiManager
import com.example.newsapplication.data.remote.dto.ArticleResponse
import com.example.newsapplication.data.remote.dto.SourcesResponse
import com.example.newsapplication.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiManager: ApiManager
) : NewsRepository {
    override suspend fun getSources(categoryId: String): SourcesResponse {
        return apiManager.getSources(apiKey = Constants.API_KEY , categoryId = categoryId)
    }

    override suspend fun getArticles(sources: String): ArticleResponse {
        return apiManager.getArticles(apiKey = Constants.API_KEY , sources = sources)
    }

}