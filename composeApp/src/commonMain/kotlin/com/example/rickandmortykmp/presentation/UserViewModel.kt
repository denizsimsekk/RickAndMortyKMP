package com.example.rickandmortykmp.presentation

import com.example.rickandmortykmp.data.model.ResponseState
import com.example.rickandmortykmp.data.repository.UserRepository

class UserViewModel(private val repository: UserRepository) : BaseViewModel() {

    init {
        observeUsers()
    }

    fun observeUsers() {
        launchWithLoading(
            onError = { error ->
                //  updateState { copy(isLoading = false, error = error.message) }
            }
        ) {
            when (val result = repository.getUsers()) {
                is ResponseState.Success -> {
                    println("chars: ${result.data}")
                    //updateState { copy(isLoading = false, error = null) }
                }

                is ResponseState.Error -> {

                }

                else -> {}
            }
        }
    }
}