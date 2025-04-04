package com.abhiram.databeatassessment.feature_home.data

import com.abhiram.databeatassessment.core.util.Endpoint
import com.abhiram.databeatassessment.feature_home.data.model.GetNewsResponseDto
import com.abhiram.databeatassessment.feature_home.domain.HomeService
import com.abhiram.databeatassessment.core.util.handleApi
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import io.ktor.http.appendPathSegments

class HomeServiceImpl(
    private val httpClient: HttpClient
) : HomeService {
    override suspend fun getTopHeadlines(
        searchQuery: String,
        country: String?,
        category: String?,
        pageSize: Int,
        pageNumber: Int
    ): Result<GetNewsResponseDto> {
        return httpClient.handleApi {
            url {
                appendPathSegments(Endpoint.TOP_HEADLINES)
                parameters.apply {
                    if (searchQuery.isNotBlank()) {
                        append(name = "q", value = searchQuery)
                    }
                    country?.let {
                        append(name = "country", value = it)
                    }
                    category?.let {
                        append(name = "category", value = it)
                    }
                    append(name = "pageSize", value = pageSize.toString())
                    append(name = "page", value = pageNumber.toString())
                }
            }
            method = HttpMethod.Get
        }
    }
}