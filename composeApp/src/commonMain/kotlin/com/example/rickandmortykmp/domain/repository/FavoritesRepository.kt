package com.example.rickandmortykmp.domain.repository

import com.example.rickandmortykmp.domain.model.CharacterViewEntity
import com.example.rickandmortykmp.domain.model.ResponseState
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getAllFavorites(): Flow<ResponseState<List<CharacterViewEntity>>>
    fun isFavorite(characterId: Int): Boolean
    fun addFavorite(character: CharacterViewEntity)
    fun removeFavorite(characterId: Int)
}
