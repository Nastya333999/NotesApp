package com.notescollection.app.notes.presentation.create_note

import androidx.compose.ui.text.input.TextFieldValue
import com.notescollection.app.notes.presentation.noteList.models.NoteUiModel

data class CreateNoteState(
    val title: TextFieldValue = TextFieldValue(""),
    val description: TextFieldValue = TextFieldValue(""),
    val noteForChange: NoteUiModel? = null,
    val noteMode: NotesMode = NotesMode.READ
)

enum class NotesMode { EDIT, READ, CREATE }