package com.example.rickandmortykmp.data.repository

import com.example.rickandmortykmp.data.model.ResponseState
abstract class BaseRepository {

    protected suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): ResponseState<T> {
        return try {
            ResponseState.Success(apiCall())
        } catch (e: Exception) {
            ResponseState.Error(e)
        }
    }
}