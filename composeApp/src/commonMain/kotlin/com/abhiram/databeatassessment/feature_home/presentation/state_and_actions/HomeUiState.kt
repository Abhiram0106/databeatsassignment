package com.abhiram.databeatassessment.feature_home.presentation.state_and_actions

import com.abhiram.databeatassessment.core.util.CountryData
import com.abhiram.databeatassessment.core.util.UiText
import com.abhiram.databeatassessment.feature_home.domain.NewsCategories

data class HomeUiState(
    val searchQuery: String = "",
    val selectedCountry: CountryData = CountryData.World,
    val selectedCategory: NewsCategories = NewsCategories.BUSINESS,
    val showCountryPickerDialog: Boolean = false,
    val snackBarMessage: UiText? = null
)
