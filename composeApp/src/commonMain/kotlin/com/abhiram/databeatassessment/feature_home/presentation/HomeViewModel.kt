package com.abhiram.databeatassessment.feature_home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import app.cash.paging.PagingConfig
import androidx.paging.cachedIn
import app.cash.paging.PagingData
import com.abhiram.databeatassessment.feature_home.domain.HomeRepository
import com.abhiram.databeatassessment.feature_home.domain.TopHeadlinesPagingSource
import com.abhiram.databeatassessment.feature_home.domain.model.NewsItem
import com.abhiram.databeatassessment.feature_home.presentation.state_and_actions.HomeUiAction
import com.abhiram.databeatassessment.feature_home.presentation.state_and_actions.HomeUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

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


    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val topHeadlinesFlow: Flow<PagingData<NewsItem>> = combine(
        uiState
            .map {
                it.searchQuery
            }
            .debounce(300.milliseconds),
        uiState.map { it.selectedCountry },
        uiState.map { it.selectedCategory }
    ) { searchQuery, selectedCountry, selectedCategory ->
        Triple(searchQuery, selectedCountry, selectedCategory)
    }.distinctUntilChanged()
        .flatMapLatest { (searchQuery, selectedCountry, selectedCategory) ->
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                ),
                pagingSourceFactory = {
                    TopHeadlinesPagingSource(
                        query = searchQuery,
                        selectedCountry = selectedCountry,
                        selectedCategory = selectedCategory,
                        homeRepository = homeRepository
                    )
                }
            ).flow
        }.cachedIn(viewModelScope)

}