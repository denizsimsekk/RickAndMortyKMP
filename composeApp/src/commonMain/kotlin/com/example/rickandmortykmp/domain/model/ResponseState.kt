package com.example.rickandmortykmp.domain.model

sealed class ResponseState<out T> {
    data class Success<T>(val data: T) : ResponseState<T>()
    data class Error(val exception: Throwable) : ResponseState<Nothing>()
}
