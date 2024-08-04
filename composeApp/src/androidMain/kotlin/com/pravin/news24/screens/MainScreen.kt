package com.pravin.news24.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pravin.news24.NewsViewModel
import com.pravin.news24.cache.News

@Composable
fun MainScreen(
    viewModel: NewsViewModel,
    onNavigateClick: (String) -> Unit
) {
    val newsList = viewModel.state.value.news
    val currentNews = viewModel.currentNews.value
    val lazyListState = rememberLazyListState()
    if (currentNews != null) {
        NewsDetailScreen(
            newsItem = currentNews,
            onBackClick = viewModel::onBackClick,
        )
    } else {
        NewsListScreen(
            newsList,
            viewModel::onNewsClick,
            lazyListState,
            onNavigateClick
        )
    }
}