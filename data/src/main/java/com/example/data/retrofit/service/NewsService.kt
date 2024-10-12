package com.example.data.retrofit.service


import com.example.data.BuildConfig
import com.example.data.retrofit.response.TopHeadlinesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines/")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): Response<TopHeadlinesResponse>
}