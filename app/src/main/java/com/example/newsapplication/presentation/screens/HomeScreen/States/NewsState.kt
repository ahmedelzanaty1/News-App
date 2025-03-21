package com.example.newsapplication.presentation.screens.HomeScreen.States

import com.example.newsapplication.domain.model.SourceModel

data class NewsState(
    val sources: List<SourceModel?> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)