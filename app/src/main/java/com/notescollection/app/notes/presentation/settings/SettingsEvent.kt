package com.notescollection.app.notes.presentation.settings

interface SettingsEvent {
    data object OnLogoutEvent : SettingsEvent
    data object OnBackEvent : SettingsEvent
}

