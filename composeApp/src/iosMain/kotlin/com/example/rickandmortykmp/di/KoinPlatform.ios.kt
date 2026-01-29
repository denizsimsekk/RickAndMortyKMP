package com.example.rickandmortykmp.di

import org.koin.core.KoinApplication

actual fun KoinApplication.applyPlatformConfig() {
    // No-op on iOS
}
