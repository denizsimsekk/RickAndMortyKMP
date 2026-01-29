package com.example.rickandmortykmp.domain.usecase

import com.example.rickandmortykmp.domain.model.CharacterViewEntity
import com.example.rickandmortykmp.domain.model.ResponseState
import com.example.rickandmortykmp.domain.repository.CharacterRepository
import com.example.rickandmortykmp.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCharacterByIdUseCase(
    private val characterRepository: CharacterRepository,
    private val favoritesRepository: FavoritesRepository
) {

    operator fun invoke(id: Int): Flow<ResponseState<CharacterViewEntity>> = flow {
        characterRepository.getCharacter(id).collect { result ->
            when (result) {
                is ResponseState.Success -> {
                    val isSaved = favoritesRepository.isFavorite(result.data.id)
                    emit(ResponseState.Success(result.data.copy(isFavorite = isSaved)))
                }
                is ResponseState.Error -> emit(result)
            }
        }
    }
}
