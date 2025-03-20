package com.example.newsapplication.presentation.screens.HomeScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.domain.use_cases.GetSourcesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.State
import com.example.newsapplication.conestant.Resource
import com.example.newsapplication.domain.model.SourceModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getsourceUseCase: GetSourcesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(NewsState())
    val state: State<NewsState> = _state

    fun getSources(categoryId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            when (val result = getsourceUseCase(categoryId)) {
                is Resource.Success -> {
                    Log.d("HomeViewModel", "Sources fetched: ${result.data?.sources}")
                    val sourceList = result.data?.sources?.mapNotNull { sourceDto ->
                        sourceDto?.name?.let { SourceModel(name = it) }
                    } ?: emptyList()

                    _state.value = _state.value.copy(
                        sources = sourceList,
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message,
                        isLoading = false
                    )
                }

                is Resource.Loading -> TODO()
            }
        }
    }
}
