package com.example.millienews.di.data

import com.example.data.retrofit.api.NewsApi
import com.example.data.retrofit.api.NewsApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ApiModule {

    @Binds
    @Singleton
    fun bindNewsApi(newsApiImpl: NewsApiImpl): NewsApi
}