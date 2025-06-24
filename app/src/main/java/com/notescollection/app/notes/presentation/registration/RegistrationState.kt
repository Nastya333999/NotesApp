package com.notescollection.app.notes.presentation.registration

data class RegistrationState(
    val userName: String = "",
    val email: String = "a.dmytriieva.a@gmail.com",
    val password: String = "123QWEqwe!@#",
    val repeatPassword: String = "123QWEqwe!@#",

    val isUserNameError: Boolean = false,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isRepeatPasswordError: Boolean = false,

    val isButtonCreateAccountEnabled: Boolean = false,

    val userNameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val repeatPasswordError: String? = null,
)