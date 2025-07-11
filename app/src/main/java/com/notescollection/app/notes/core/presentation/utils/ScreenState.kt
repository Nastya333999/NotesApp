package com.notescollection.app.notes.core.presentation.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

sealed interface LoadingUiState<out T> {

    data object Loading : LoadingUiState<Nothing>

    data class Loaded<T>(
        val data: T
    ) : LoadingUiState<T>
}

inline fun <T> MutableStateFlow<LoadingUiState<T>>.updateLoadedState(
    block: (T) -> T
) {
    this.update { state ->
        when (state) {
            is LoadingUiState.Loaded -> state.copy(data = block(state.data))
            else -> state
        }
    }
}