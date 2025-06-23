package com.notescollection.app.notes.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isError: Boolean = false,
)