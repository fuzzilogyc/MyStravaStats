package org.fuzz.mystravastats.di

import android.content.Context
import android.content.SharedPreferences
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.fuzz.mystravastats.auth.OAuthRepository
import org.fuzz.mystravastats.data.LocalStore
import org.fuzz.mystravastats.data.SharedPreferencesLocalStore
import org.fuzz.mystravastats.data.StravaService
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {

    single<StravaService> {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.strava.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()

        retrofit.create<StravaService>(StravaService::class.java)
    }

    single<SharedPreferences> {
        androidApplication().getSharedPreferences("org.fuzz.mystravastats.app_prefs", Context.MODE_PRIVATE)
    }

    factory<LocalStore> { SharedPreferencesLocalStore(get()) }

    factory { OAuthRepository(get(), get()) }

}