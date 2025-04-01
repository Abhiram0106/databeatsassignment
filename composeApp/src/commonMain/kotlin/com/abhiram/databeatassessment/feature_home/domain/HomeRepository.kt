package com.abhiram.databeatassessment.feature_home.domain

import com.abhiram.databeatassessment.core.util.CountryData
import com.abhiram.databeatassessment.feature_home.domain.model.GetNewsResponse

interface HomeRepository {
    suspend fun getTopHeadlines(
        searchQuery: String,
        country: CountryData,
        category: NewsCategories,
        pageSize: Int,
        pageNumber: Int
    ): Result<GetNewsResponse>
}