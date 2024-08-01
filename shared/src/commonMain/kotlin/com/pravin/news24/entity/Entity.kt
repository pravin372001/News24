package com.pravin.news24.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsOne(
    @SerialName("nextPage")
    val nextPage: String,
    @SerialName("results")
    val results: List<Result>,
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int
)

@Serializable
data class Result(
    @SerialName("ai_org")
    val aiOrg: String? = "",
    @SerialName("ai_region")
    val aiRegion: String? = "",
    @SerialName("ai_tag")
    val aiTag: String? = "",
    @SerialName("article_id")
    val articleId: String? = "",
    @SerialName("category")
    val category: List<String>? = emptyList(),
    @SerialName("content")
    val content: String? = "",
    @SerialName("country")
    val country: List<String>? = emptyList(),
    @SerialName("creator")
    val creator: List<String>? = emptyList(),
    @SerialName("description")
    val description: String? = "",
    @SerialName("duplicate")
    val duplicate: Boolean? = false,
    @SerialName("image_url")
    val imageUrl: String? = "",
    @SerialName("keywords")
    val keywords: List<String>? = emptyList(),
    @SerialName("language")
    val language: String? = "",
    @SerialName("link")
    val link: String? = "",
    @SerialName("pubDate")
    val pubDate: String? = "",
    @SerialName("sentiment")
    val sentiment: String? = "",
    @SerialName("sentiment_stats")
    val sentimentStats: String? = "",
    @SerialName("source_icon")
    val sourceIcon: String? = "",
    @SerialName("source_id")
    val sourceId: String? = "",
    @SerialName("source_priority")
    val sourcePriority: Int? = 0,
    @SerialName("source_url")
    val sourceUrl: String? = "",
    @SerialName("title")
    val title: String? = "",
    @SerialName("video_url")
    val videoUrl: String? = ""
)

