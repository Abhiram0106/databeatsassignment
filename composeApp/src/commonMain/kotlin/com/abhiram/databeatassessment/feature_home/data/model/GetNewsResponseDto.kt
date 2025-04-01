package com.abhiram.databeatassessment.feature_home.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GetNewsResponseDto(
    val articles: List<ArticlesDto>
)

@Serializable
data class ArticlesDto(
    val source: ArticleSourceDto,
    val urlToImage: String,
    val title: String,
    val description: String?,
    val publishedAt: String,
    val url: String
)

@Serializable
data class ArticleSourceDto(
    val name: String
)
