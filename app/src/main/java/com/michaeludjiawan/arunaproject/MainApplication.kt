package com.michaeludjiawan.arunaproject

import android.app.Application
import com.michaeludjiawan.arunaproject.di.featureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(featureModule))
        }
    }
}