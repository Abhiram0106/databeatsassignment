package com.abhiram.databeatassessment.feature_home.data

import com.abhiram.databeatassessment.core.util.Endpoint
import com.abhiram.databeatassessment.feature_home.data.model.GetNewsResponseDto
import com.abhiram.databeatassessment.feature_home.domain.HomeService
import com.abhiram.databeatassessment.feature_home.util.handleApi
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class HomeServiceImpl(
    private val httpClient: HttpClient
) : HomeService {
    override suspend fun getEverything(): Result<GetNewsResponseDto> {
        return httpClient.handleApi {
            url(Endpoint.EVERYTHING)
            method = HttpMethod.Get
        }
    }
}