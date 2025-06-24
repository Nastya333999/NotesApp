package com.notescollection.app.notes.data.request

import kotlinx.serialization.Serializable

sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Failure(
        val message: String,
        val cause: Throwable? = null
    ) : NetworkResult<Nothing>
}

@Serializable
data class ErrorResponse(val error: String)
