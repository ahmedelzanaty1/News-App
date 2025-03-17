package com.example.newsapplication.domain.repository

import com.example.newsapplication.data.remote.dto.ArticleResponse
import com.example.newsapplication.data.remote.dto.NewsResponse

interface NewsRepository {
    suspend fun getSources(categoryId: String): NewsResponse
    suspend fun getArticles(sources: String): ArticleResponse
}
