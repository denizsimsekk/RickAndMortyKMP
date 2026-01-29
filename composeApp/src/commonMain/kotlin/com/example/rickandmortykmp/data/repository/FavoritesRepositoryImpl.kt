package com.example.rickandmortykmp.data.repository

import com.example.rickandmortykmp.data.local.FavoritesDataSource
import com.example.rickandmortykmp.domain.model.CharacterViewEntity
import com.example.rickandmortykmp.domain.model.ResponseState
import com.example.rickandmortykmp.domain.repository.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class FavoritesRepositoryImpl(
    private val dataSource: FavoritesDataSource
) : FavoritesRepository {

    override fun getAllFavorites(): Flow<ResponseState<List<CharacterViewEntity>>> = flow {
        try {
            val list = dataSource.getAllFavorites()
            emit(ResponseState.Success(list))
        } catch (e: Throwable) {
            emit(ResponseState.Error(e))
        }
    }

    override fun isFavorite(characterId: Int): Boolean = dataSource.isFavorite(characterId)

    override fun addFavorite(character: CharacterViewEntity) = dataSource.insertFavorite(character)

    override fun removeFavorite(characterId: Int) = dataSource.removeFavorite(characterId)
}
