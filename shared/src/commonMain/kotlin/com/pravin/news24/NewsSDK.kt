package com.pravin.news24

import com.pravin.news24.cache.Database
import com.pravin.news24.cache.DatabaseDriverFactory
import com.pravin.news24.cache.News
import com.pravin.news24.entity.NewsOne
import com.pravin.news24.network.NewsApi

class NewsSDK(databaseDriverFactory: DatabaseDriverFactory, private val api: NewsApi) {
    private val database = Database(databaseDriverFactory)

    @Throws(Exception::class)
    suspend fun getNews(apikey: String, language: String = "en", country: String = "in"): List<News> {
        val cachedNews = database.getAllNews()
        return cachedNews.ifEmpty {
            formatNews(api.getNews(apikey, language, country)).also {
                database.clearAndCreateNews(it)
            }
        }
    }

    private fun formatNews(news: NewsOne): List<News> {
        val formattedNews = mutableListOf<News>()
        news.results.forEach {
            formattedNews.add(
                News(
                    newsId = 0,
                    articleId = it.articleId ?: "",
                    title = it.title ?: "",
                    category = (it.category ?: it.category?.first()).toString(),
                    content = it.content ?: "",
                    country = it.country.toString(),
                    creator = (it.creator ?: it.creator?.first()).toString(),
                    description = it.description ?: "",
                    image_url = it.imageUrl ?: "",
                    pubDate = it.pubDate ?: "",
                    link = it.link ?: ""
                )
            )
        }
        return formattedNews
    }
}