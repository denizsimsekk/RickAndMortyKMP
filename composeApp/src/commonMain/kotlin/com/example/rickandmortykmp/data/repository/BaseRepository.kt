package com.example.rickandmortykmp.data.repository

import com.example.rickandmortykmp.data.model.ResponseState

/**
 * Base data layer helper used by concrete repositories.
 *
 * - Wraps API calls into a sealed [ResponseState] to keep error handling
 *   consistent across the app.
 * - Can be extended with logging / mapping without touching call sites.
 */
abstract class BaseRepository {

    /**
     * Execute a suspending API call and wrap the result in [ResponseState].
     */
    protected suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): ResponseState<T> =
        try {
            ResponseState.Success(apiCall())
        } catch (e: Exception) {
            ResponseState.Error(e)
        }

    /**
     * Execute a suspending API call, then map the successful body
     * to another type while still using [ResponseState] for errors.
     */
    protected suspend fun <T, R> safeApiCall(
        apiCall: suspend () -> T,
        mapper: (T) -> R
    ): ResponseState<R> =
        try {
            val body = apiCall()
            ResponseState.Success(mapper(body))
        } catch (e: Exception) {
            ResponseState.Error(e)
        }
}