package com.notescollection.app.notes.presentation.noteList

import androidx.paging.PagingData
import com.notescollection.app.notes.presentation.noteList.models.NoteUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class NoteListState(
    val userName : String = "",
    val notesFlow: Flow<PagingData<NoteUiModel>> = flowOf()
)