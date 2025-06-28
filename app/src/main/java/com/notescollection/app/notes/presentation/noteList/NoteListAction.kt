package com.notescollection.app.notes.presentation.noteList

import com.notescollection.app.notes.presentation.noteList.models.NoteUiModel

sealed interface NoteListAction {
    data object OnNewNoteCreate : NoteListAction
    data class OnDeleteNote(val note: NoteUiModel) : NoteListAction
    data class OnNoteClick(val note: NoteUiModel) : NoteListAction
}