package com.abhiram.databeatassessment.feature_home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhiram.databeatassessment.core.util.CountryData
import com.abhiram.databeatassessment.core.util.UiText
import com.abhiram.databeatassessment.feature_home.domain.HomeRepository
import com.abhiram.databeatassessment.feature_home.domain.NewsCategories
import com.abhiram.databeatassessment.feature_home.presentation.state_and_actions.HomeUiAction
import com.abhiram.databeatassessment.feature_home.presentation.state_and_actions.HomeUiState
import com.abhiram.databeatassessment.feature_home.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        listenToQueriesAndFetchNews()
    }

    fun clearSnackBar() {
        _uiState.update {
            it.copy(snackBarMessage = null)
        }
    }

    fun onUiAction(action: HomeUiAction) {
        when (action) {
            is HomeUiAction.OnQueryChanged -> {
                _uiState.update {
                    it.copy(searchQuery = action.query)
                }
            }

            is HomeUiAction.OnSelectCategory -> {
                _uiState.update {
                    it.copy(selectedCategory = action.category)
                }
            }

            HomeUiAction.OnDismissCountryPickerDialog -> {
                _uiState.update {
                    it.copy(showCountryPickerDialog = false)
                }
            }

            is HomeUiAction.OnSelectCountry -> {
                _uiState.update {
                    it.copy(selectedCountry = action.country)
                }
            }

            HomeUiAction.OnShowCountryPickerDialog -> {
                _uiState.update {
                    it.copy(showCountryPickerDialog = true)
                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun listenToQueriesAndFetchNews() {
        combine(
            uiState
                .map { it.searchQuery }
                .debounce(300.milliseconds),
            uiState.map { it.selectedCountry },
            uiState.map { it.selectedCategory }
        ) { searchQuery, selectedCountry, selectedCategory ->
            Triple(searchQuery, selectedCountry, selectedCategory)
        }
            .distinctUntilChanged()
            .onEach { (searchQuery, selectedCountry, selectedCategory) ->
                getTopHeadlines(
                    searchQuery = searchQuery,
                    country = selectedCountry,
                    category = selectedCategory
                )
            }
            .launchIn(viewModelScope)
    }

    private fun getTopHeadlines(
        searchQuery: String,
        country: CountryData,
        category: NewsCategories
    ) = viewModelScope.launch(dispatcher) {
        _uiState.update {
            it.copy(isLoading = true)
        }

        homeRepository.getTopHeadlines(
            searchQuery = searchQuery,
            country = country,
            category = category
        ).onSuccess { data ->
            _uiState.update {
                it.copy(newsItems = data.articles)
            }
        }.onFailure { failure ->
            failure.message?.let { msg ->
                _uiState.update {
                    it.copy(snackBarMessage = UiText.DynamicString(msg))
                }
            }
        }

        _uiState.update {
            it.copy(isLoading = false)
        }
    }
}