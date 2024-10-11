package com.example.data.retrofit.response

import  com.example.domain.model.Article as DomainArticle

data class TopHeadlinesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article> = listOf()
)

data class Article(
    val source: Source? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
) {
    fun toDomainModel(): DomainArticle =
        DomainArticle(
            source = source?.name ?: "",
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            content = content
        )
}

data class Source(
    val id: String? = null,
    val name: String? = null
)