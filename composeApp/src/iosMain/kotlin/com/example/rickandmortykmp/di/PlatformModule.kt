package com.example.rickandmortykmp.di

import com.example.rickandmortykmp.data.local.DatabaseDriverFactory
import com.example.rickandmortykmp.data.local.IosDatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun getPlatformModule(): Module = module {
    single<DatabaseDriverFactory> { IosDatabaseDriverFactory() }
}
