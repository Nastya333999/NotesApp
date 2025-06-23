package com.notescollection.app.core.data.response

@kotlinx.serialization.Serializable
data class AuthResponse(
    val accessToken: String,
    val refreshToken: String
)
