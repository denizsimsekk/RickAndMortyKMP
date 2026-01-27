package com.example.rickandmortykmp.presentation.characters

import com.example.rickandmortykmp.domain.model.Character

data class CharactersUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val errorMessage: String? = null
)

