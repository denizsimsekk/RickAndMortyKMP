package com.example.rickandmortykmp.domain.repository

import com.example.rickandmortykmp.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(): List<Character>
}

