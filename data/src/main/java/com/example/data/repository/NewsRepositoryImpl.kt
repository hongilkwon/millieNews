package com.example.data.repository

import android.util.Log
import com.example.data.datasource.NewsLocalDataSource
import com.example.data.datasource.NewsRemoteDataSource
import com.example.data.db.entity.toDomainObj
import com.example.data.retrofit.response.toEntity
import com.example.domain.model.Article
import com.example.domain.repository.NewsRepository
import com.example.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {

    private val TAG = "NewsRepositoryImpl"

    override suspend fun updateArticles(country: String) {
        val ret = newsRemoteDataSource.fetchLatestNews()

        when (ret) {
            is ApiResult.Success -> {
                val articles = ret.data.articles
                if (articles.isNotEmpty()) {
                    val newArticles = newsLocalDataSource.filterNewArticles(ret.data.articles)
                    Log.d(TAG, "updateArticles::ApiResult.Success::newArticles::${newArticles}")

                    val imageUrls = newArticles.map { it.urlToImage ?: "" }
                    val images = newsRemoteDataSource.fetchImagesConcurrently(imageUrls)

                    val articleEntities =
                        newArticles.mapIndexed { idx, article -> article.toEntity(image = images[idx]) }
                    newsLocalDataSource.saveArticles(articleEntities)
                }
            }

            is ApiResult.Failure -> {
                Log.d(TAG, "updateArticles::ApiResult.Failure::message:${ret.error.message}")
            }
        }
    }

    override fun getAllArticlesByFlow(): Flow<List<Article>> =
        newsLocalDataSource.getAllArticlesByFlow().map { entities ->
            entities.map { it.toDomainObj() }
        }
}
