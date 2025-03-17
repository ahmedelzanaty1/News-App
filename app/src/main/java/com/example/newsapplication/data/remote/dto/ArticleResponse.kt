package com.example.newsapplication.data.remote.dto

import com.example.newsapplication.domain.model.ArticleModel
import com.google.gson.annotations.SerializedName

data class ArticleResponse(

	@SerializedName("totalResults")
	val totalResults: Int? = null,

	@SerializedName("articles")
	val articles: List<ArticlesItem> = emptyList(),

	@SerializedName("status")
	val status: String? = null
)

data class ArticlesItem(

	@SerializedName("publishedAt")
	val publishedAt: String? = null,

	@SerializedName("author")
	val author: String? = null,

	@SerializedName("urlToImage")
	val urlToImage: String? = null,

	@SerializedName("description")
	val description: String? = null,

	@SerializedName("source")
	val source: Source? = null,

	@SerializedName("title")
	val title: String? = null,

	@SerializedName("url")
	val url: String? = null,

	@SerializedName("content")
	val content: String? = null
)

data class Source(

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("id")
	val id: String? = null
)
fun ArticlesItem.toArticleModel() = ArticleModel(
	title = title ?: "",
	description = description ?: "",
	urlToImage = urlToImage ?: "",
	publishedAt = publishedAt ?: "",
	sourceName = source?.name ?: "",
	url = url ?: ""
)
