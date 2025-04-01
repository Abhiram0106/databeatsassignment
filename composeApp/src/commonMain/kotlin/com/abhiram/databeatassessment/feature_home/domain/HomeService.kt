package com.abhiram.databeatassessment.feature_home.domain

import com.abhiram.databeatassessment.feature_home.data.model.GetNewsResponseDto

interface HomeService {
    suspend fun getEverything(): Result<GetNewsResponseDto>
}