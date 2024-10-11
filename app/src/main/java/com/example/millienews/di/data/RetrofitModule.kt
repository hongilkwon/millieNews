package com.example.millienews.di.data

import com.example.data.retrofit.api.NewsApi
import com.example.data.retrofit.api.NewsApiImpl
import com.example.data.retrofit.service.NewsService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    @Named("NEWS_API_URI")
    fun provideNewsApiUri(): String = "https://newsapi.org/v2/"

    @Singleton
    @Provides
    fun provideGson(): Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    @Singleton
    @Provides
    fun provideConverterFactory(
        gson: Gson
    ): Converter.Factory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("NEWS_API_URI") apiUrl: String,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(converterFactory)
        .build()

    @Singleton
    @Provides
    fun provideNewsService(
        retrofit: Retrofit
    ): NewsService = retrofit.create(NewsService::class.java)

//    @Singleton
//    @Provides
//    fun provideNewsApi(
//        newsApiImpl: NewsApiImpl
//    ): NewsApi = newsApiImpl
}