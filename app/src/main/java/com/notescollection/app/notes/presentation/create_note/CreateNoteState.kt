package com.notescollection.app.notes.presentation.create_note

import com.notescollection.app.notes.presentation.noteList.models.NoteUiModel

data class CreateNoteState(
    val title: String = "Note Title",
    val description: String = "",
    val noteForChange: NoteUiModel? = null
)