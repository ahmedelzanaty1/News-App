package com.example.newsapplication.presentation.screens.HomeScreen.States

import com.example.newsapplication.domain.model.ArticleModel

data class ArticleState(
    val sources: List<ArticleModel?> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)