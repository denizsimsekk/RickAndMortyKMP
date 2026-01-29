package com.example.rickandmortykmp.presentation.favorites

import com.example.rickandmortykmp.domain.model.CharacterViewEntity

data class FavoritesUiState(
    val isLoading: Boolean = false,
    val characters: List<CharacterViewEntity> = emptyList(),
    val errorMessage: String? = null
)