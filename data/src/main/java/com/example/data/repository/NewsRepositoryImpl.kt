package com.example.data.repository

import com.example.data.retrofit.api.NewsApi
import com.example.domain.model.Article
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {

    override suspend fun updateArticles(
        country: String,
    ) {
        val ret = newsApi.getTopHeadlines()

        // TODO
        // 성공시 - 이미지 URL로 Bitmap 이미지로 받아서, Room에 저장한다.
        // 실패시 - 실패 로그
    }

    override fun getArticlesByFlow(country: String): Flow<List<Article>> {
        TODO("Not yet implemented")
    }


}