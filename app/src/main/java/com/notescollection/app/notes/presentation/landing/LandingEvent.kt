package com.notescollection.app.notes.presentation.landing

interface LandingEvent {
    data object GetStartedClick : LandingEvent
    data object LoginClick : LandingEvent
}