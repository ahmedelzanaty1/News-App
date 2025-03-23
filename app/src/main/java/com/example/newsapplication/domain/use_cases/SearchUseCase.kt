package com.example.newsapplication.domain.use_cases

import com.example.newsapplication.conestant.Resource
import com.example.newsapplication.data.remote.dto.ArticleResponse
import com.example.newsapplication.domain.repository.NewsRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(query: String , page: Int) : Resource<ArticleResponse> {
        return try {
            val response = newsRepository.searchArticles(query = query , page = page)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("Something went wrong")
        }catch (e: IOException) {
            Resource.Error("Check your internet connection")
        } catch (e: HttpException) {
            Resource.Error("Server error: ${e.code()}")
        }
    }
}