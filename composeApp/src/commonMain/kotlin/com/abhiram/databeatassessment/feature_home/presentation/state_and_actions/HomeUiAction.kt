package com.abhiram.databeatassessment.feature_home.presentation.state_and_actions

sealed class HomeUiAction {
    data class OnQueryChanged(val query: String): HomeUiAction()
}