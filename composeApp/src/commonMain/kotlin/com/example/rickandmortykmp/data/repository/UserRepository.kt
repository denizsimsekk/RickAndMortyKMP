package com.example.rickandmortykmp.data.repository

import com.example.rickandmortykmp.data.model.ResponseState
import com.example.rickandmortykmp.data.model.UserResponse
import com.example.rickandmortykmp.data.remote.ApiService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserRepository : BaseRepository(), KoinComponent {

    private val apiService: ApiService by inject()

    suspend fun getUsers(): ResponseState<UserResponse> =
        safeApiCall {
            apiService.getCharacters()
        }
}