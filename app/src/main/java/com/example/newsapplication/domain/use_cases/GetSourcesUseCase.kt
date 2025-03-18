package com.example.newsapplication.domain.use_cases

import com.example.newsapplication.conestant.Resource
import com.example.newsapplication.data.remote.dto.NewsResponse
import com.example.newsapplication.domain.repository.NewsRepository
import java.io.IOException
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(categoryId: String): Resource<NewsResponse> {
        return try {
            val response = newsRepository.getSources(categoryId)
            Resource.Success(response)
        } catch (e: IOException) {
            Resource.Error("Check your internet connection")
        } catch (e: retrofit2.HttpException) {
            Resource.Error("Server error: ${e.code()}")
        } catch (e: Exception) {
            Resource.Error("Something went wrong")
        }
    }
}
