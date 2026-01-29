package com.example.rickandmortykmp.di

import com.example.rickandmortykmp.data.local.AndroidDatabaseDriverFactory
import com.example.rickandmortykmp.data.local.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

actual fun getPlatformModule(): Module = module {
    single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(androidContext()) }
}
