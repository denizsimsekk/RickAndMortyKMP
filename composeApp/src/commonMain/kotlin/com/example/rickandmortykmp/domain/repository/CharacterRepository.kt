package com.example.rickandmortykmp.domain.repository

import com.example.rickandmortykmp.domain.model.CharacterViewEntity
import com.example.rickandmortykmp.domain.model.ResponseState
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): Flow<ResponseState<List<CharacterViewEntity>>>
    fun getCharacter(id: Int): Flow<ResponseState<CharacterViewEntity>>
}

