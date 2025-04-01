package com.abhiram.databeatassessment.feature_home.presentation

import androidx.lifecycle.ViewModel
import com.abhiram.databeatassessment.feature_home.presentation.state_and_actions.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(): ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun clearSnackBar() {
        _uiState.update {
            it.copy(snackBarMessage = null)
        }
    }
}