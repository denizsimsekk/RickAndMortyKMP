package com.example.rickandmortykmp.presentation

import com.example.rickandmortykmp.data.repository.UserRepository

class UserViewModel(
    private val repository: UserRepository
) : BaseViewModel() {

    init {
        observeUsers()
    }

    fun observeUsers() {
        collectResponse(
            request = { repository.getUsers() },
            onSuccess = { data ->
                println("chars: $data")
                // updateState { copy(isLoading = false, error = null) }
            },
            onError = { error ->
                // updateState { copy(isLoading = false, error = error.message) }
            }
        )
    }
}