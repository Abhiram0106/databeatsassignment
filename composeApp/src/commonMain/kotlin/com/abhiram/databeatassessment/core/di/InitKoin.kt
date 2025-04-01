package com.abhiram.databeatassessment.core.di

import com.abhiram.databeatassessment.feature_home.di.homeModules
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            httpClientModule,
            homeModules,
        )
    }
}