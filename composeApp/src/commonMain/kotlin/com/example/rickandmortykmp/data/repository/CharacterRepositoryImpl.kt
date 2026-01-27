package com.example.rickandmortykmp.data.repository

import com.example.rickandmortykmp.data.mapper.toDomain
import com.example.rickandmortykmp.data.remote.ApiService
import com.example.rickandmortykmp.domain.model.Character
import com.example.rickandmortykmp.domain.repository.CharacterRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterRepositoryImpl : CharacterRepository, KoinComponent {

    private val apiService: ApiService by inject()

    override suspend fun getCharacters(): List<Character> =
        apiService.getCharacters().results.map { it.toDomain() }
}

