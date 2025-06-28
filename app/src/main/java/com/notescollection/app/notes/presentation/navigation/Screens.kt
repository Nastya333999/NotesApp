package com.notescollection.app.notes.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screens {

    @Serializable
    data object SplashScreen : Screens

    @Serializable
    data object LandingScreen : Screens

    @Serializable
    data object LoginScreen : Screens

    @Serializable
    data object RegisterScreen : Screens

    @Serializable
    data object NoteListScreen : Screens

    @Serializable
    data class CreateNoteScreen(val noteId: String? = null) : Screens {
        companion object {
            const val routeBase = "createNote"
            const val noteIdArg = "noteId"

            fun route(noteId: String? = null): String =
                if (noteId != null) "$routeBase/$noteId" else routeBase
        }
    }
}