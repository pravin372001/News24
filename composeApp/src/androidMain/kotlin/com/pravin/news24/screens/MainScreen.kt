package com.pravin.news24.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pravin.news24.cache.News

@Composable
fun MainScreen(
    currentNews: News?,
    onNewsClick: (News) -> Unit,
    onBackClick: () -> Unit,
    newsList: List<News>
) {
    if (currentNews != null) {
        NewsDetailScreen(
            newsItem = currentNews,
            onBackClick = onBackClick
        )
    } else {
        NewsListScreen(
            newsList,
            onNewsClick
        )
    }
}