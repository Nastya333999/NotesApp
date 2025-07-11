package com.notescollection.app.notes.presentation.create_note

interface CreateNoteEvent {
    data object OnSaveClick : CreateNoteEvent
    data object OnCancelClick : CreateNoteEvent
    data class ShowToast(val message: String) : CreateNoteEvent
}