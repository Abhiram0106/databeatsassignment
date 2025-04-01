package com.abhiram.databeatassessment

import android.app.Application
import com.abhiram.databeatassessment.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApp)
        }
    }
}