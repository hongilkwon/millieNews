package com.example.data.retrofit.response

import android.graphics.Bitmap
import com.example.data.db.entity.ArticleEntity
import com.example.data.db.entity.generateArticleId
import com.google.gson.annotations.SerializedName


data class TopHeadlinesResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<ArticleDTO> = listOf()
)

data class ArticleDTO(
    @SerializedName("source")
    val source: SourceDTO? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("urlToImage")
    val urlToImage: String? = null,
    @SerializedName("publishedAt")
    val publishedAt: String? = null,
    @SerializedName("content")
    val content: String? = null
)

data class SourceDTO(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null
)

fun ArticleDTO.toEntity(image: Bitmap? = null): ArticleEntity =
    ArticleEntity(
        source = source?.name,
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        bitMapImage = image,
        publishedAt = publishedAt,
        content = content
    )