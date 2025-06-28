package com.notescollection.app.notes.presentation.registration

data class RegistrationState(
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",

    val isUserNameError: Boolean = false,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isRepeatPasswordError: Boolean = false,

    val isButtonCreateAccountEnabled: Boolean = false,

    val userNameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val repeatPasswordError: String? = null,

    val isPasswordVisible: Boolean = false,
    val isRepeatPasswordVisible: Boolean = false
)