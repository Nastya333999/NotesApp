package com.notescollection.app.notes.presentation.registration


interface RegistrationEvent {
    data object OnLoginClick : RegistrationEvent
    data object OnCreateAccount : RegistrationEvent
    data class ShowToast(val message: String) :RegistrationEvent
}