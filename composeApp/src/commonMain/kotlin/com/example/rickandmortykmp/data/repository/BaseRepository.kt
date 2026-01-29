package com.example.rickandmortykmp.data.repository

import com.example.rickandmortykmp.domain.model.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository {

    protected fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Flow<ResponseState<T>> = flow {
        try {
            emit(ResponseState.Success(apiCall()))
        } catch (e: Exception) {
            emit(ResponseState.Error(e))
        }
    }.flowOn(Dispatchers.IO)
}