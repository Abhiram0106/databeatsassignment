package com.abhiram.databeatassessment

import androidx.compose.ui.window.ComposeUIViewController
import com.abhiram.databeatassessment.core.di.initKoin
import com.abhiram.databeatassessment.core.presentation.data_beat_assessment_app.App
import org.koin.compose.KoinContext

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    KoinContext {
        App()
    }
}