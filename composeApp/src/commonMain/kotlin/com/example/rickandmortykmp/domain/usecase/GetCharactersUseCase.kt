package com.example.rickandmortykmp.domain.usecase

import com.example.rickandmortykmp.domain.model.Character
import com.example.rickandmortykmp.domain.repository.CharacterRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetCharactersUseCase : KoinComponent {

    private val repository: CharacterRepository by inject()

    suspend operator fun invoke(): List<Character> = repository.getCharacters()
}

