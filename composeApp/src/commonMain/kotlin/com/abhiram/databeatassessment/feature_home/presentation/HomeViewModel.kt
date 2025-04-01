package com.abhiram.databeatassessment.feature_home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhiram.databeatassessment.core.util.UiText
import com.abhiram.databeatassessment.feature_home.domain.HomeRepository
import com.abhiram.databeatassessment.feature_home.presentation.state_and_actions.HomeUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getEverything()
    }

    fun clearSnackBar() {
        _uiState.update {
            it.copy(snackBarMessage = null)
        }
    }

    private fun getEverything() = viewModelScope.launch(dispatcher) {
        _uiState.update {
            it.copy(isLoading = true)
        }

        homeRepository.getEverything()
            .onSuccess { data ->
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