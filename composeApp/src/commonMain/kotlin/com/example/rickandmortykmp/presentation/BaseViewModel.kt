package com.example.rickandmortykmp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortykmp.data.model.ResponseState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Cross‑platform base class for MVVM ViewModels backed by the
 * multiplatform Lifecycle `ViewModel` and `viewModelScope`.
 */
open class BaseViewModel : ViewModel() {
    /**
     * Helper to run a suspending request that returns [ResponseState] and
     * invoke the proper callback based on success / error.
     */
    protected fun <T> collectResponse(
        request: suspend () -> ResponseState<T>,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit = {}
    ) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            onError(throwable)
        }

        viewModelScope.launch(exceptionHandler) {
            when (val result = request()) {
                is ResponseState.Success -> onSuccess(result.data)
                is ResponseState.Error -> onError(result.exception)
            }
        }
    }

    /**
     * Collect a [Flow] with shared error handling.
     */
    protected fun <T> collectFlow(
        source: Flow<T>,
        onEach: (T) -> Unit,
        onError: (Throwable) -> Unit = {}
    ) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            onError(throwable)
        }

        viewModelScope.launch(exceptionHandler) {
            source.collect { value -> onEach(value) }
        }
    }

    /**
     * Optional hook if you want an explicit clear call from iOS / Compose.
     * This simply cancels [viewModelScope].
     */
    open fun clear() {
        viewModelScope.cancel()
    }
}