package com.notescollection.app.notes.presentation.noteList

import com.notescollection.app.notes.presentation.noteList.models.NoteUiModel

interface NoteListEvent {
    data object OnNewNoteEvent : NoteListEvent
    data class OnNoteCLick(val note: NoteUiModel) : NoteListEvent
}