package com.abhiram.databeatassessment.feature_home.presentation.state_and_actions

import com.abhiram.databeatassessment.core.util.CountryData
import com.abhiram.databeatassessment.feature_home.domain.NewsCategories

sealed class HomeUiAction {
    data class OnQueryChanged(val query: String) : HomeUiAction()
    data object OnShowCountryPickerDialog : HomeUiAction()
    data object OnDismissCountryPickerDialog : HomeUiAction()
    data class OnSelectCountry(val country: CountryData) : HomeUiAction()
    data class OnSelectCategory(val category: NewsCategories) : HomeUiAction()
}