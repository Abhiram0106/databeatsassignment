package com.abhiram.databeatassessment.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsErrorResponse(
    val status: String,
    val code: String,
    val message: String
)