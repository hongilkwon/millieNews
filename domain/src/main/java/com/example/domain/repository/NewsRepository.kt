package com.example.domain.repository

import com.example.domain.model.Article
import com.example.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow


interface NewsRepository {

    suspend fun updateArticles(country: String = "us")

    fun getAllArticlesByFlow(): Flow<List<Article>>
}