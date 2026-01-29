package com.example.rickandmortykmp.presentation.characterdetail

import com.example.rickandmortykmp.domain.model.CharacterViewEntity

data class CharacterDetailUiState(
    val isLoading: Boolean = false,
    val character: CharacterViewEntity? = null,
    val errorMessage: String? = null,
    val isSaved: Boolean = false
)