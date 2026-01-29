package com.example.rickandmortykmp.domain.usecase

import com.example.rickandmortykmp.domain.repository.FavoritesRepository

class IsFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    operator fun invoke(characterId: Int): Boolean =
        repository.isFavorite(characterId)
}
