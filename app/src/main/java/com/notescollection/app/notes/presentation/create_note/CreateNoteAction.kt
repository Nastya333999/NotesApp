package com.notescollection.app.notes.presentation.create_note

sealed interface CreateNoteAction {
    data class OnTitleChange(val title: String) : CreateNoteAction
    data class OnDescriptionChange(val description: String) : CreateNoteAction

    data object OnSaveClick : CreateNoteAction
    data object OnCancelClick : CreateNoteAction

    data object NavigateBack : CreateNoteAction
}