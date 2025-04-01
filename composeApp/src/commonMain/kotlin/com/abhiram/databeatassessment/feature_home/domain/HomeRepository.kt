package com.abhiram.databeatassessment.feature_home.domain

import com.abhiram.databeatassessment.feature_home.domain.model.GetNewsResponse

interface HomeRepository {
    suspend fun getEverything(): Result<GetNewsResponse>
}