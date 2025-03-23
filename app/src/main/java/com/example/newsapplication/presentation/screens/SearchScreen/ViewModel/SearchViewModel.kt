package com.example.newsapplication.presentation.screens.SearchScreen.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.conestant.Resource
import com.example.newsapplication.data.remote.dto.ArticleResponse
import com.example.newsapplication.domain.use_cases.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _searchState = MutableStateFlow<Resource<ArticleResponse>>(Resource.Loading())
    val searchState: StateFlow<Resource<ArticleResponse>> = _searchState

    fun searchArticles(query: String, page: Int) {
        viewModelScope.launch {
            _searchState.value = Resource.Loading()
            val response = searchUseCase(query, page)
            _searchState.value = response
        }
    }
}