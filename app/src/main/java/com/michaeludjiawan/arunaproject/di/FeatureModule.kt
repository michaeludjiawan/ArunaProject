package com.michaeludjiawan.arunaproject.di

import com.michaeludjiawan.arunaproject.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel { HomeViewModel() }
}