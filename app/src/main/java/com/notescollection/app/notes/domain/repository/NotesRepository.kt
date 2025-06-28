package com.notescollection.app.notes.domain.repository

import androidx.paging.PagingData
import com.notescollection.app.notes.domain.models.NoteModel
import com.notescollection.app.notes.domain.models.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun createNote(title: String, description: String): ResultWrapper<Unit>
    suspend fun updateNote(note: NoteModel): ResultWrapper<Unit>
    suspend fun deleteNote(noteId: String): ResultWrapper<Unit>
    suspend fun getNoteById(noteId: String): ResultWrapper<NoteModel?>
    suspend fun syncUnsyncedNotes() : ResultWrapper<Unit>

    fun getPagedNotes(): Flow<PagingData<NoteModel>>
}