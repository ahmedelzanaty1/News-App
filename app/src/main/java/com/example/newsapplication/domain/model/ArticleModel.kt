package com.example.newsapplication.domain.model

data class ArticleModel(
    val title: String,
    val description: String,
    val urlToImage: String?,
    val publishedAt: String,
    val sourceName: String,
    val url: String
)
