package com.abhiram.databeatassessment.feature_home.di

import com.abhiram.databeatassessment.core.util.DiDispatchers
import com.abhiram.databeatassessment.feature_home.data.HomeRepositoryImpl
import com.abhiram.databeatassessment.feature_home.data.HomeServiceImpl
import com.abhiram.databeatassessment.feature_home.domain.HomeRepository
import com.abhiram.databeatassessment.feature_home.domain.HomeService
import com.abhiram.databeatassessment.feature_home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val homeModules = module {
    single<HomeService> {
        HomeServiceImpl(get())
    }
    single<HomeRepository> {
        HomeRepositoryImpl(get())
    }
    viewModel {
        HomeViewModel(
            homeRepository = get(),
            dispatcher = get(named(DiDispatchers.IO))
        )
    }
}