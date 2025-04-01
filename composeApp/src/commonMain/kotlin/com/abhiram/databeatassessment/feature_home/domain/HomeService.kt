package com.abhiram.databeatassessment.feature_home.domain

import com.abhiram.databeatassessment.feature_home.data.model.GetNewsResponseDto

interface HomeService {
    suspend fun getTopHeadlines(
        searchQuery: String,
        country: String?,
        category: String?,
        pageSize: Int,
        pageNumber: Int
    ): Result<GetNewsResponseDto>
}