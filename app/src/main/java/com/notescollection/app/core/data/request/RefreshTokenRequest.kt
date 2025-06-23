package com.notescollection.app.core.data.request

@kotlinx.serialization.Serializable
data class RefreshTokenRequest(
    val refreshToken: String
)
