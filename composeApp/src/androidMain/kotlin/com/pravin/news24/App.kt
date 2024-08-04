package com.pravin.news24

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pravin.news24.screens.AddNewsScreen
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
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screens.Home.route) {
                composable(Screens.Home.route) {
                    MainScreen(
                        viewModel,
                        onNavigateClick = { navController.navigate(it) }
                        )
                }
                composable(Screens.AddNews.route) {
                    AddNewsScreen(onNavigateClick = { navController.navigate(it) })
                }
            }
        }
    }
}

sealed class Screens(var route:String){
    data object Home: Screens("home")
    data object AddNews: Screens("addNews")
}

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
