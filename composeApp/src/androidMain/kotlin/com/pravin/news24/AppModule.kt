package com.pravin.news24

import com.pravin.news24.cache.AndroidDatabaseDriverFactory
import com.pravin.news24.network.NewsApi
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<NewsApi> {
        NewsApi()
    }
    single<NewsSDK> {
        NewsSDK(databaseDriverFactory = AndroidDatabaseDriverFactory(androidContext()), api =  get())
    }
    viewModel {
        NewsViewModel(newsSDK = get())
    }
}