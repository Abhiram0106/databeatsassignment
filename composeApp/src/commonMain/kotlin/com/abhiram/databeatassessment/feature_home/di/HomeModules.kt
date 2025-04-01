package com.abhiram.databeatassessment.feature_home.di

import com.abhiram.databeatassessment.feature_home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModules = module {
    viewModel {
        HomeViewModel()
    }
}