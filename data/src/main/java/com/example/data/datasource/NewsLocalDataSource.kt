package com.example.data.datasource

import com.example.data.db.AppDatabase
import com.example.data.db.entity.ArticleEntity
import com.example.data.retrofit.response.ArticleDTO
import com.example.domain.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsLocalDataSource @Inject constructor(
    private val database: AppDatabase
) {

    suspend fun filterNewArticles(articleDTOList: List<ArticleDTO>): List<ArticleDTO> =
        withContext(Dispatchers.IO) {
            articleDTOList.filter {
                !database.articleDao().containsByUrl(it.url ?: "")
            }
        }


    fun getAllArticlesByFlow() =
        database.articleDao().getAllByFlow()


    suspend fun saveArticles(articleEntity: List<ArticleEntity>) =
        database.articleDao().insertAll(articleEntity)
}