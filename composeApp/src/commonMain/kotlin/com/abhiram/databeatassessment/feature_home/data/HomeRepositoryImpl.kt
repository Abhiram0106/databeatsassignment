package com.abhiram.databeatassessment.feature_home.data

import com.abhiram.databeatassessment.feature_home.domain.HomeRepository
import com.abhiram.databeatassessment.feature_home.domain.HomeService
import com.abhiram.databeatassessment.feature_home.domain.model.GetNewsResponse
import com.abhiram.databeatassessment.feature_home.domain.toGetNewsResponse

class HomeRepositoryImpl(
    private val homeService: HomeService
) : HomeRepository {
    override suspend fun getEverything(): Result<GetNewsResponse> {
        return homeService.getEverything().toGetNewsResponse()
    }
}