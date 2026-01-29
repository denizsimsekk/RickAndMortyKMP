package com.example.rickandmortykmp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortykmp.domain.model.ResponseState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    fun <T> Flow<ResponseState<T>>.collectData(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit = {}
    ) {
        viewModelScope.launch {
            collect { result ->
                when (result) {
                    is ResponseState.Success -> onSuccess(result.data)
                    is ResponseState.Error -> onError(result.exception)
                }
            }
        }
    }

}

