package com.example.rickandmortykmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.rickandmortykmp.di.koin
import com.example.rickandmortykmp.presentation.characters.CharactersScreen
import com.example.rickandmortykmp.presentation.characters.CharactersViewModel

@Composable
fun App(viewModel: CharactersViewModel = remember { koin.get() }) {
    MaterialTheme {
        CharactersScreen(viewModel = viewModel)
    }
}