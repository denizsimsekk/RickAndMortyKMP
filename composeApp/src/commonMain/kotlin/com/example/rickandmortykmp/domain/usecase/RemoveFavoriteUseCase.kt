package com.example.rickandmortykmp.domain.usecase

import com.example.rickandmortykmp.domain.repository.FavoritesRepository

class RemoveFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    operator fun invoke(characterId: Int) {
        repository.removeFavorite(characterId)
    }
}
