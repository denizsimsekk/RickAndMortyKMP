package com.example.rickandmortykmp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    protected fun launchWithLoading(
        onError: (Throwable) -> Unit = {},
        block: suspend () -> Unit
    ) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            onError(throwable)
        }

        viewModelScope.launch(exceptionHandler) {
            block()
        }
    }
}