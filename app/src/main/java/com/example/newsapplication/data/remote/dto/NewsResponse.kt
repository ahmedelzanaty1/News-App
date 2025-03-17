package com.example.newsapplication.data.remote.dto

import com.example.newsapplication.domain.model.SourceModel
import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @SerializedName("sources")
    val sources: List<SourcesItem> = emptyList(),

    @SerializedName("status")
    val status: String? = null
)

data class SourcesItem(

    @SerializedName("country")
    val country: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("language")
    val language: String? = null,

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("category")
    val category: String? = null,

    @SerializedName("url")
    val url: String? = null
)
fun SourcesItem.toSourceModel() = SourceModel(
    name = name ?: ""
)
