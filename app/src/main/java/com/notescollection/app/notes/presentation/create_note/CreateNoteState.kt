package com.notescollection.app.notes.presentation.create_note

import androidx.compose.ui.text.input.TextFieldValue
import com.notescollection.app.notes.presentation.noteList.models.NoteUiModel

data class CreateNoteState(
    val title: TextFieldValue = TextFieldValue(""),
    val description: String = "",
    val noteForChange: NoteUiModel? = null
)