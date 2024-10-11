package com.example.domain.model

import android.graphics.Bitmap

data class Article(
    val id: Int,
    val source: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val bitmapImage: Bitmap?,
    val publishedAt: String,
    val content: String
)
