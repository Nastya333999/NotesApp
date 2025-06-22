package com.notescollection.app.notes.presentation.navigation

import kotlinx.serialization.Serializable

internal sealed interface Screens {

    @Serializable
    data object SplashScreen : Screens

    @Serializable
    data object LandingScreen : Screens

    @Serializable
    data object LoginScreen : Screens

    @Serializable
    data object RegisterScreen : Screens
}