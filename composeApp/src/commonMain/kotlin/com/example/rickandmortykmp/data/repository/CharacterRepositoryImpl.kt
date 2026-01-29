package com.example.rickandmortykmp.data.repository

import com.example.rickandmortykmp.data.mapper.toDomain
import com.example.rickandmortykmp.data.remote.ApiService
import com.example.rickandmortykmp.domain.model.CharacterViewEntity
import com.example.rickandmortykmp.domain.model.ResponseState
import com.example.rickandmortykmp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(
    private val apiService: ApiService
) : BaseRepository(), CharacterRepository {

    override fun getCharacters(): Flow<ResponseState<List<CharacterViewEntity>>> =
        safeApiCall {
            apiService.getCharacters().results.map { it.toDomain() }
        }

    override fun getCharacter(id: Int): Flow<ResponseState<CharacterViewEntity>> =
        safeApiCall {
            apiService.getCharacter(id).toDomain()
        }
}

