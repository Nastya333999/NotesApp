package com.notescollection.app.notes.presentation.create_note

import com.notescollection.app.notes.presentation.noteList.models.NoteUiModel

data class CreateNoteState(
    val note: NoteUiModel? = null,
    val noteMode: NotesMode = NotesMode.READ
)

enum class NotesMode { EDIT, READ, CREATE }