package com.example.rickandmortykmp.presentation.favorites

import com.example.rickandmortykmp.domain.model.CharacterViewEntity
import com.example.rickandmortykmp.domain.usecase.GetAllFavoritesUseCase
import com.example.rickandmortykmp.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FavoritesViewModel(
    private val getAllFavorites: GetAllFavoritesUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState(isLoading = true))
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    fun load() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        getAllFavorites().collectData(
            onSuccess = { list ->
                _uiState.update {
                    it.copy(isLoading = false, characters = list, errorMessage = null)
                }
            },
            onError = { e ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Unknown error"
                    )
                }
            }
        )
    }
}


