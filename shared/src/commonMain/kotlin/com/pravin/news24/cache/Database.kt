package com.pravin.news24.cache

import com.pravin.news24.entity.NewsOne

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun getAllNews(): List<News> {
        return dbQuery.getAllNews(::mapNews).executeAsList()
    }

    fun mapNews(
        newsId: Long,
        articleId: String,
        title: String,
        category: String,
        content: String,
        country: String,
        creator: String,
        description: String,
        image_url: String,
        pubDate: String,
        link: String
    ) : News {
        return News(
            articleId = articleId,
            title = title,
            category = category,
            content = content,
            country = country,
            creator = creator,
            description = description,
            image_url = image_url,
            pubDate = pubDate,
            link = link,
            newsId = newsId
        )
    }

    internal fun clearAndCreateNews(news: List<News>) {
        dbQuery.transaction {
            dbQuery.deleteAllNews()
            news.forEach { news ->
                dbQuery.insertNews(
                    articleId = news.articleId ?: "",
                    title = news.title ?: "",
                    category = news.category.toString(),
                    content = news.content ?: "",
                    country = news.country.toString(),
                    creator = news.creator.toString(),
                    description = news.description ?: "",
                    image_url = news.image_url ?: "",
                    pubDate = news.pubDate ?: "",
                    link = news.link ?: ""
                )
            }
        }
    }

    internal fun insertNews(news: List<News>) {
        dbQuery.transaction {
            news.forEach{ news ->
                dbQuery.insertNews(
                    articleId = news.articleId ?: "",
                    title = news.title ?: "",
                    category = news.category.toString(),
                    content = news.content ?: "",
                    country = news.country.toString(),
                    creator = news.creator.toString(),
                    description = news.description ?: "",
                    image_url = news.image_url ?: "",
                    pubDate = news.pubDate ?: "",
                    link = news.link ?: ""
                )
            }
        }
    }
}