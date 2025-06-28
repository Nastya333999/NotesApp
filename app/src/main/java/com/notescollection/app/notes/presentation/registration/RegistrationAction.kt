package com.notescollection.app.notes.presentation.registration

sealed interface RegistrationAction {
    data class OnUserNameChanged(val value: String) : RegistrationAction
    data class OnEmailChanged(val value: String) : RegistrationAction
    data class OnPasswordChanged(val value: String) : RegistrationAction
    data class OnRepeatPasswordChanged(val value: String) : RegistrationAction
    object OnCreateAccountClick : RegistrationAction
    object OnLoginClick : RegistrationAction

    data class OnPasswordVisibilityChange(val visible: Boolean) : RegistrationAction
    data class OnRepeatPasswordVisibilityChange(val visible: Boolean) : RegistrationAction
}