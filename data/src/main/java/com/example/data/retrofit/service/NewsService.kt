package com.example.data.retrofit.service


import com.example.data.retrofit.response.TopHeadlinesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines/")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = "96721efc7b2549ec8d5c4bb3dea6df0b",
    ): Response<TopHeadlinesResponse>
}