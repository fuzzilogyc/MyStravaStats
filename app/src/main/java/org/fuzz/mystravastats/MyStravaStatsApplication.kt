package org.fuzz.mystravastats

import android.app.Application
import org.fuzz.mystravastats.di.activityModule
import org.fuzz.mystravastats.di.appModule
import org.koin.android.ext.android.startKoin

class MyStravaStatsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, activityModule))
    }
}