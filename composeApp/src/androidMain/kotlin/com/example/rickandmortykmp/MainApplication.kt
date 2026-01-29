package com.example.rickandmortykmp

import android.app.Application
import com.example.rickandmortykmp.di.initKoin
import com.example.rickandmortykmp.di.setAndroidApplication

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setAndroidApplication(this)
        initKoin()
    }
}
