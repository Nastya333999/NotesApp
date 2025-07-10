package com.notescollection.app.notes.presentation.create_note

import androidx.compose.ui.text.input.TextFieldValue

sealed interface CreateNoteAction {
    data class OnTitleChange(val title: TextFieldValue) : CreateNoteAction
    data class OnDescriptionChange(val description: TextFieldValue) : CreateNoteAction
    data object OnSaveClick : CreateNoteAction
    data object OnCancelClick : CreateNoteAction
    data class OnModeChange(val mode: NotesMode) : CreateNoteAction
}