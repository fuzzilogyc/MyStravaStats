package org.fuzz.mystravastats.di

import org.fuzz.mystravastats.MainActivityViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val activityModule = module {
    viewModel { MainActivityViewModel(get()) }
}