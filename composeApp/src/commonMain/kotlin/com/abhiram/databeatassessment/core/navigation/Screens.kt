package com.abhiram.databeatassessment.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Graphs {
    @Serializable
    data object Onboarding : Graphs()

    @Serializable
    data object Home : Graphs()
}

@Serializable
sealed class Screens {
    @Serializable
    data object Onboarding : Screens()

    @Serializable
    data object Home : Screens()
}