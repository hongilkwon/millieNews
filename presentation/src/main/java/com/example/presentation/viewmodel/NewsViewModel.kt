package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val TAG = "NewsViewModel"

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        updateArticle()
    }

    val articlesStateFlow = newsRepository.getAllArticlesByFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = listOf()
    )

    private fun updateArticle() = viewModelScope.launch {
        _isLoading.value = true
        newsRepository.updateArticles()
        _isLoading.value = false
    }

    fun readArticle(id: Int) = viewModelScope.launch {
        val article = articlesStateFlow.value.find { it.id == id }
        article?.let { newsRepository.readArticles(it) }
    }
}