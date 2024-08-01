package com.pravin.news24

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pravin.news24.cache.News
import com.pravin.news24.entity.NewsOne
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val newsSDK: NewsSDK) : ViewModel() {

    private val _state = mutableStateOf(NewsState())
    val state: State<NewsState> = _state

    private val _currentNews = mutableStateOf<News?>(null)
    val currentNews: State<News?> = _currentNews

    init {
        loadNews()
    }

    fun onNewsClick(news: News) {
        _currentNews.value = news
    }

    fun onBackClick() {
        _currentNews.value = null
    }

    fun loadNews() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val news = newsSDK.getNews(API_KEY)
                _state.value = _state.value.copy(news = news, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(errorMessage = e.message, isLoading = false)
                Log.e("NewsViewModel", "Error loading news: ${e.message}")
            }
        }
    }

    data class NewsState(
        val news: List<News> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

    companion object {
        const val API_KEY = "pub_493099aad351d0663cebc67551d7d81abba60"
    }
}