package com.pravin.news24

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pravin.news24.screens.NewsListScreen
import com.pravin.news24.screens.MainScreen
import com.pravin.news24.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@Composable
@Preview
fun App() {
    val viewModel = koinViewModel<NewsViewModel>()
    val state by remember { viewModel.state }
    val currentNews by remember { viewModel.currentNews }
    AppTheme {
        if(state.isLoading) {
            Loading()
        } else if(state.errorMessage != null) {
            Box(modifier = Modifier.fillMaxSize()){
                Column(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = state.errorMessage.toString())
                    Button(
                        onClick = {
                            viewModel.loadNews()
                        }
                    ) {
                        Text(text = "Retry")
                    }
                }
            }
        } else {
            MainScreen(currentNews, viewModel::onNewsClick, viewModel::onBackClick, state.news)
        }
    }
}

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
