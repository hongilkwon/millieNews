package com.example.domain.repository

import com.example.domain.model.Article
import kotlinx.coroutines.flow.Flow


interface NewsRepository {

    suspend fun updateArticles(country: String = "us")

    suspend fun readArticles(article: Article)

    fun getAllArticlesByFlow(): Flow<List<Article>>
}