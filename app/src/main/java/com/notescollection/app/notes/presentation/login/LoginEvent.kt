package com.notescollection.app.notes.presentation.login

interface LoginEvent {
    data object OnLoginClick : LoginEvent
    data object OnRegisterClick : LoginEvent
}