package com.pravin.news24.network

import com.pravin.news24.cache.News
import com.pravin.news24.entity.NewsOne
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class NewsApi {

    private val httpClient = HttpClient{
        install(ContentNegotiation){
            json(
                Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                }
            )
        }
    }

    suspend fun getNews(apikey: String, language: String = "en", country: String = "in"): NewsOne {
        return httpClient.get("https://newsdata.io/api/1/latest") {
            parameter("apikey", apikey)
            parameter("language", language)
            parameter("country", country)
        }.body()
    }
}