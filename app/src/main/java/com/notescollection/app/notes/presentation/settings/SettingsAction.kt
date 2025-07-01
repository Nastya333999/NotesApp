package com.notescollection.app.notes.presentation.settings

sealed interface SettingsAction {
    data object OnLogoutClick : SettingsAction
    data object OnBackClick : SettingsAction
}