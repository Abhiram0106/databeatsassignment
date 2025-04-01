package com.abhiram.databeatassessment.feature_home.domain.model

import kotlinx.datetime.LocalDateTime

data class NewsItem(
    val sourceName: String,
    val imageUrl: String?,
    val title: String,
    val description: String?,
    val publishedAt: LocalDateTime?,
    val articleUrl: String
)