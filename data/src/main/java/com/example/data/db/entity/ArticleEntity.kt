package com.example.data.db.entity

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.domain.model.Article

@Entity(tableName = "article",
    indices=[Index(value = arrayOf("url"), unique = true)])
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "source") val source: String? = null,
    @ColumnInfo(name = "author") val author: String? = null,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "url") val url: String? = null,
    @ColumnInfo(name = "urlToImage") val urlToImage: String? = null,
    @ColumnInfo(name = "bitMapImage") val bitMapImage: Bitmap? = null,
    @ColumnInfo(name = "publishedAt") val publishedAt: String? = null,
    @ColumnInfo(name = "content") val content: String? = null
)

fun ArticleEntity.toDomainObj(): Article =
    Article(
        id = id,
        source = source ?: "",
        author = author ?: "",
        title = title ?: "",
        description = description ?: "",
        url = url ?: "",
        urlToImage = urlToImage ?: "",
        bitmapImage = bitMapImage,
        publishedAt = publishedAt ?: "",
        content = content ?: "",
    )


fun generateArticleId(title: String?, publishedAt: String?): String {
    val titlePart = title?.take(10)?.replace("\\s".toRegex(), "") ?: "defaultTitle"
    return "$publishedAt-$titlePart"
}