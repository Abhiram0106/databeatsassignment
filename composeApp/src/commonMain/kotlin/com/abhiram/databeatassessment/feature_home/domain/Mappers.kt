package com.abhiram.databeatassessment.feature_home.domain

import com.abhiram.databeatassessment.feature_home.data.model.ArticlesDto
import com.abhiram.databeatassessment.feature_home.data.model.GetNewsResponseDto
import com.abhiram.databeatassessment.feature_home.domain.model.GetNewsResponse
import com.abhiram.databeatassessment.feature_home.domain.model.NewsItem
import com.abhiram.databeatassessment.feature_home.util.stringToLocalDate

fun ArticlesDto.toDomain(): NewsItem = NewsItem(
    sourceName = source.name,
    imageUrl = urlToImage,
    title = title,
    description = description,
    publishedAt = stringToLocalDate(publishedAt),
    articleUrl = url
)

fun GetNewsResponseDto.toDomain(): GetNewsResponse = GetNewsResponse(
    articles = articles
)