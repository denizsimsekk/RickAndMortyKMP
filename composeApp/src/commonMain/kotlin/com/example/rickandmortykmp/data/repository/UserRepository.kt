package com.example.rickandmortykmp.data.repository

import com.example.rickandmortykmp.data.model.ResponseState
import com.example.rickandmortykmp.data.model.UserResponse
import com.example.rickandmortykmp.data.remote.ApiService

class UserRepository(
    private val apiService: ApiService
) : BaseRepository() {

    suspend fun getUsers(): ResponseState<UserResponse> {
        return safeApiCall {
            apiService.getUsers()
        }
    }

}