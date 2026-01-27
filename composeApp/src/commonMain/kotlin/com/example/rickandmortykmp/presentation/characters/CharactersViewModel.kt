package com.example.rickandmortykmp.presentation.characters

import androidx.lifecycle.viewModelScope
import com.example.rickandmortykmp.domain.usecase.GetCharactersUseCase
import com.example.rickandmortykmp.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharacters: GetCharactersUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(CharactersUiState(isLoading = true))
    val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
        viewModelScope.launch {
            try {
                val characters = getCharacters()
                _uiState.value = CharactersUiState(isLoading = false, characters = characters)
            } catch (e: Throwable) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error"
                )
            }
        }
    }
}

