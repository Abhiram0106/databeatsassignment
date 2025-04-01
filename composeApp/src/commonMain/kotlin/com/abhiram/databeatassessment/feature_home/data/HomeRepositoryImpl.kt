package com.abhiram.databeatassessment.feature_home.data

import com.abhiram.databeatassessment.core.util.CountryData
import com.abhiram.databeatassessment.feature_home.domain.HomeRepository
import com.abhiram.databeatassessment.feature_home.domain.HomeService
import com.abhiram.databeatassessment.feature_home.domain.NewsCategories
import com.abhiram.databeatassessment.feature_home.domain.model.GetNewsResponse
import com.abhiram.databeatassessment.feature_home.domain.toGetNewsResponse

class HomeRepositoryImpl(
    private val homeService: HomeService
) : HomeRepository {

    override suspend fun getTopHeadlines(
        searchQuery: String,
        country: CountryData,
        category: NewsCategories,
        pageSize: Int,
        pageNumber: Int
    ): Result<GetNewsResponse> {
        return homeService.getTopHeadlines(
            searchQuery = searchQuery,
            country = country.countryCodeAlpha2,
            category = category.query,
            pageSize = pageSize,
            pageNumber = pageNumber
        ).toGetNewsResponse()
    }
}