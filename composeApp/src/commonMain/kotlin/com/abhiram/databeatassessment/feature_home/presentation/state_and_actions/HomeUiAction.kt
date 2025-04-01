package com.abhiram.databeatassessment.feature_home.presentation.state_and_actions

import com.abhiram.databeatassessment.feature_home.domain.NewsCategories

sealed class HomeUiAction {
    data class OnQueryChanged(val query: String): HomeUiAction()
    data class OnSelectCategory(val categories: NewsCategories): HomeUiAction()
}