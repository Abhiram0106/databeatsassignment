package com.abhiram.databeatassessment.core.di

import com.abhiram.databeatassessment.core.util.DiDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {
    single<CoroutineDispatcher>(named(DiDispatchers.DEFAULT)) { Dispatchers.Default }
    single<CoroutineDispatcher>(named(DiDispatchers.IO)) { Dispatchers.IO }
    single<CoroutineDispatcher>(named(DiDispatchers.MAIN)) { Dispatchers.Main }
}