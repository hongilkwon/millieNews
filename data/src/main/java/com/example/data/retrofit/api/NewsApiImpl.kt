package com.example.data.retrofit.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.data.retrofit.response.TopHeadlinesResponse
import com.example.data.retrofit.service.NewsService
import com.example.data.util.ApiException
import com.example.data.util.ApiException.Companion.RESPONSE_IS_NULL
import com.example.data.util.ApiException.Companion.UNKNOWN_ERROR_CODE
import com.example.domain.util.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.HttpException
import javax.inject.Inject

class NewsApiImpl @Inject constructor(
    private val service: NewsService
) : NewsApi {

    private val TAG = "NewsApiImpl"

    override suspend fun getTopHeadlines(
        country: String,
    ): ApiResult<TopHeadlinesResponse> = withContext(Dispatchers.IO) {
        try {
            val response = service.getTopHeadlines(country)

            val data = response.body()
            if (data != null) {
                ApiResult.Success(data)
            } else {
                ApiResult.Failure(ApiException(code = RESPONSE_IS_NULL, message = "data is null"))
            }
        } catch (e: HttpException) {
            ApiResult.Failure(ApiException(code = e.code(), message = e.message()))
        } catch (e: Exception) {
            ApiResult.Failure(ApiException(code = UNKNOWN_ERROR_CODE, message = "Unknown_Error"))
        }
    }
}