package com.notescollection.app.core.data.request

@kotlinx.serialization.Serializable
data class Tokens(
    val accessToken: String,
    val refreshToken: String
)
