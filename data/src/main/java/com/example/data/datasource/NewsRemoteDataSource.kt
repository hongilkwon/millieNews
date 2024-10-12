package com.example.data.datasource

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.data.retrofit.api.NewsApi
import com.example.data.retrofit.response.TopHeadlinesResponse
import com.example.domain.util.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRemoteDataSource @Inject constructor(
    private val newsApi: NewsApi,
) {

    private val TAG = "NewsRemoteDataSource"
    private val client = OkHttpClient()

    suspend fun fetchLatestNews(): ApiResult<TopHeadlinesResponse> = withContext(Dispatchers.IO) {
        newsApi.getTopHeadlines()
    }

    suspend fun fetchImagesConcurrently(urls: List<String>) = withContext(Dispatchers.IO) {
        val deferredImages = urls.mapIndexed{ idx, url ->
            async {
                downloadBitmapImage(idx, url)
            }
        }
        val images = deferredImages.awaitAll()
        images
    }

    private suspend fun downloadBitmapImage(idx: Int, url: String): Bitmap? =
        withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()
                val inputStream = response.body?.byteStream()
                BitmapFactory.decodeStream(inputStream)?.let {
                    Bitmap.createScaledBitmap(it, 500, 500, true)
                }
            } catch (e: Exception) {
                Log.d(ContentValues.TAG, "downloadBitmapImage::error::${e.message}")
                null
            }
        }
}


