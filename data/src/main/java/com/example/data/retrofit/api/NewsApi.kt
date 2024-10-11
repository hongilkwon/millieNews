package com.example.data.retrofit.api

import com.example.data.retrofit.response.TopHeadlinesResponse
import com.example.domain.util.ApiResult

interface NewsApi {
    suspend fun getTopHeadlines(country: String = "us"): ApiResult<TopHeadlinesResponse>
}