package com.example.rickandmortykmp.presentation.characters

import com.example.rickandmortykmp.domain.usecase.GetCharactersUseCase
import com.example.rickandmortykmp.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CharactersViewModel(
    private val getCharacters: GetCharactersUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(CharactersUiState(isLoading = true))
    val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    fun refresh() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        getCharacters().collectData(
            onSuccess = { characters ->
                _uiState.update { CharactersUiState(isLoading = false, characters = characters) }
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

