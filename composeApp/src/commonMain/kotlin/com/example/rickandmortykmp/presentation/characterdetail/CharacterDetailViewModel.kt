package com.example.rickandmortykmp.presentation.characterdetail

import androidx.lifecycle.viewModelScope
import com.example.rickandmortykmp.domain.model.CharacterViewEntity
import com.example.rickandmortykmp.domain.usecase.AddFavoriteUseCase
import com.example.rickandmortykmp.domain.usecase.GetCharacterByIdUseCase
import com.example.rickandmortykmp.domain.usecase.RemoveFavoriteUseCase
import com.example.rickandmortykmp.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getCharacterById: GetCharacterByIdUseCase,
    private val addFavorite: AddFavoriteUseCase,
    private val removeFavorite: RemoveFavoriteUseCase,
    private val characterId: Int
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(CharacterDetailUiState(isLoading = true))
    val uiState: StateFlow<CharacterDetailUiState> = _uiState.asStateFlow()

    fun load() {
        getCharacterById(characterId).collectData(
            onSuccess = { character ->
                _uiState.update {
                    CharacterDetailUiState(
                        isLoading = false,
                        character = character,
                        isSaved = character.isFavorite == true
                    )
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

    fun toggleSave() {
        val character = _uiState.value.character ?: return
        viewModelScope.launch {
            if (_uiState.value.isSaved) {
                removeFavorite(character.id)
            } else {
                addFavorite(character)
            }
            _uiState.update { it.copy(isSaved = !it.isSaved) }
        }
    }
}


