package com.example.newsapplication.presentation.screens.HomeScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.domain.use_cases.GetSourcesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.State
import com.example.newsapplication.conestant.Resource
import com.example.newsapplication.domain.model.ArticleModel
import com.example.newsapplication.domain.model.SourceModel
import com.example.newsapplication.domain.use_cases.GetArticlesUseCase
import com.example.newsapplication.presentation.screens.HomeScreen.States.ArticleState
import com.example.newsapplication.presentation.screens.HomeScreen.States.NewsState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getsourceUseCase: GetSourcesUseCase,
    private val getarticleusecase: GetArticlesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(NewsState())
    val state: State<NewsState> = _state

    private val _articleState = mutableStateOf(ArticleState())
    val articleState: State<ArticleState> = _articleState

    fun getSources(categoryId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            Log.d("API Request", "Fetching sources for category: $categoryId")

            when (val result = getsourceUseCase(categoryId)) {
                is Resource.Success -> {
                    val sourceList = result.data?.sources?.mapNotNull { sourceDto ->
                        if (sourceDto?.id != null && sourceDto.name != null) {
                            SourceModel(id = sourceDto.id, name = sourceDto.name)
                        } else null
                    } ?: emptyList()

                    _state.value = _state.value.copy(
                        sources = sourceList,
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message,
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }
    }

    fun getArticles(sources: String) {
        viewModelScope.launch {
            _articleState.value = _articleState.value.copy(isLoading = true, error = null)
            Log.d("API Request", "Fetching articles from source: $sources")
            when (val result = getarticleusecase(sources)) {
                is Resource.Success -> {
                    Log.d("API Response", "Success! Articles fetched from $sources: ${result.data?.articles}");                   val articleList = result.data?.articles?.mapNotNull { articleDto ->
                        if (articleDto.title != null && articleDto.url != null) {
                            ArticleModel(
                                title = articleDto.title,
                                description = articleDto.description ?: "No Description",
                                urlToImage = articleDto.urlToImage ?: "",
                                publishedAt = articleDto.publishedAt ?: "",
                                sourceName = articleDto.source?.name ?: "",
                                url = articleDto.url
                            )
                        } else null
                    } ?: emptyList()

                    _articleState.value = _articleState.value.copy(
                        sources = articleList,
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    _articleState.value = _articleState.value.copy(
                        error = result.message,
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    _articleState.value = _articleState.value.copy(isLoading = true)
                }
            }
        }
    }
}


