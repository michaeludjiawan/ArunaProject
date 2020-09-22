package com.michaeludjiawan.arunaproject.di

import com.michaeludjiawan.arunaproject.data.repository.PostRepository
import com.michaeludjiawan.arunaproject.data.repository.PostRepositoryImpl
import com.michaeludjiawan.arunaproject.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel { HomeViewModel(get()) }

    single<PostRepository> { PostRepositoryImpl(get(), get(), get()) }
}