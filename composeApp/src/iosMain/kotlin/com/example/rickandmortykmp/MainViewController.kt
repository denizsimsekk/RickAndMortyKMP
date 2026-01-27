package com.example.rickandmortykmp

import androidx.compose.ui.window.ComposeUIViewController
import com.example.rickandmortykmp.di.ensureKoin

fun MainViewController() = ComposeUIViewController {
    ensureKoin()
    App()
}