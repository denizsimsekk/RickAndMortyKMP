package com.example.rickandmortykmp.domain.usecase

import com.example.rickandmortykmp.domain.model.CharacterViewEntity
import com.example.rickandmortykmp.domain.repository.FavoritesRepository

class AddFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    operator fun invoke(character: CharacterViewEntity) {
        repository.addFavorite(character)
    }
}
