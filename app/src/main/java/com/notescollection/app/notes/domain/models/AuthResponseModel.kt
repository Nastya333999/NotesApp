package com.notescollection.app.notes.domain.models

import com.notescollection.app.notes.data.response.AuthResponse

data class AuthResponseModel(
    val accessToken: String,
    val refreshToken: String
)

fun AuthResponse.toModel(): AuthResponseModel {
    return AuthResponseModel(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}