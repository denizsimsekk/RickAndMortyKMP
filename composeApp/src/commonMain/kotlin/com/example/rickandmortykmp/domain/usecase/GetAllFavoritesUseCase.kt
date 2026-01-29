package com.example.rickandmortykmp.domain.usecase

import com.example.rickandmortykmp.domain.model.CharacterViewEntity
import com.example.rickandmortykmp.domain.model.ResponseState
import com.example.rickandmortykmp.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavoritesUseCase(
    private val repository: FavoritesRepository
) {
    operator fun invoke(): Flow<ResponseState<List<CharacterViewEntity>>> =
        repository.getAllFavorites()
}
