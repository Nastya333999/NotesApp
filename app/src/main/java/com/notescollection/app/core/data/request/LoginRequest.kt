package com.notescollection.app.core.data.request

@kotlinx.serialization.Serializable
data class LoginRequest(
    val email: String,
    val password: String
)
