package com.example.newsapplication.domain.repository

import com.example.newsapplication.data.remote.dto.ArticleResponse
import com.example.newsapplication.data.remote.dto.SourcesResponse

interface NewsRepository {
    suspend fun getSources(categoryId: String): SourcesResponse
    suspend fun getArticles(sources: String): ArticleResponse
    suspend fun searchArticles(query: String , page: Int): ArticleResponse
}
