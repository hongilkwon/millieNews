package com.example.data.datasource

import com.example.data.db.AppDatabase
import com.example.data.db.entity.ArticleEntity
import com.example.data.retrofit.response.ArticleDTO
import com.example.domain.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
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
        database.articleDao().getAllByFlow().flowOn(Dispatchers.IO)


    suspend fun saveArticles(articleEntities: List<ArticleEntity>) = withContext(Dispatchers.IO) {
        database.articleDao().insertAll(articleEntities)
    }


    suspend fun updateArticle(article: Article) = withContext(Dispatchers.IO) {
        val entity = ArticleEntity(
            id = article.id,
            source = article.source.ifEmpty { null },
            author = article.author.ifEmpty { null },
            title = article.title.ifEmpty { null },
            description = article.description.ifEmpty { null },
            url = article.url.ifEmpty { null },
            urlToImage = article.urlToImage.ifEmpty { null },
            bitMapImage = article.bitmapImage,
            publishedAt = article.publishedAt.ifEmpty { null },
            content = article.content.ifEmpty { null },
            isRead = article.isRead
        )
        database.articleDao().insert(entity)
    }
}