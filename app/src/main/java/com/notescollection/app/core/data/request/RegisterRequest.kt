package com.notescollection.app.core.data.request

@kotlinx.serialization.Serializable
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)
