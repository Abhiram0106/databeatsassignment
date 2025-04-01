package com.abhiram.databeatassessment.feature_home.presentation.state_and_actions

import com.abhiram.databeatassessment.core.util.UiText
import com.abhiram.databeatassessment.feature_home.domain.model.NewsItem

data class HomeUiState(
    val searchQuery: String = "",
    val newsItems: List<NewsItem> = emptyList(),
    val isLoading: Boolean = false,
    val snackBarMessage: UiText? = null
)
