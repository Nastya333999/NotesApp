package com.notescollection.app.notes.presentation.landing

sealed interface LandingAction {
    data object GetStartedClick : LandingAction
    data object LoginClick : LandingAction
}