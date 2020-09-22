package com.michaeludjiawan.arunaproject

import android.app.Application
import com.michaeludjiawan.arunaproject.di.dataModule
import com.michaeludjiawan.arunaproject.di.featureModule
import com.michaeludjiawan.arunaproject.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(dataModule, networkModule, featureModule))
        }
    }
}