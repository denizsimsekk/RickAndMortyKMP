package com.example.rickandmortykmp.data.model
sealed class ResponseState<out T> {
    data class Success<T>(val data: T) : ResponseState<T>()
    data class Error(val exception: Throwable) : ResponseState<Nothing>()
}