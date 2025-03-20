package com.example.newsapplication.domain.use_cases

import android.util.Log
import com.example.newsapplication.conestant.Resource
import com.example.newsapplication.data.remote.dto.SourcesResponse
import com.example.newsapplication.domain.repository.NewsRepository
import java.io.IOException
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(categoryId: String): Resource<SourcesResponse> {
        return try {
            val response = newsRepository.getSources(categoryId)
            Log.d("GetSourcesUseCase", "Response: $response")
            Resource.Success(response)
        } catch (e: IOException) {
            Resource.Error("Check your internet connection")
        } catch (e: retrofit2.HttpException) {
            Log.e("GetSourcesUseCase", "HttpException: ${e.message()}", e)
            Resource.Error("Server error: ${e.code()}")
        } catch (e: Exception) {
            Log.e("GetSourcesUseCase", "Exception: ${e.message}", e)
            Resource.Error("Something went wrong")
        }
    }
}
