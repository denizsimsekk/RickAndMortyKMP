package com.example.rickandmortykmp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication

private var _androidApplication: Application? = null

fun setAndroidApplication(application: Application) {
    _androidApplication = application
}

actual fun KoinApplication.applyPlatformConfig() {
    androidContext(_androidApplication!!)
}
