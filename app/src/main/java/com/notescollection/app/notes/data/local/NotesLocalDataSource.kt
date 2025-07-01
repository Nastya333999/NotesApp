package com.notescollection.app.notes.data.local

import androidx.paging.PagingSource
import com.notescollection.app.notes.data.database.NoteEntity
import com.notescollection.app.notes.domain.models.NoteModel

interface NotesLocalDataSource {
    suspend fun insertNote(note: NoteModel)
    suspend fun updateNote(note: NoteModel)
    suspend fun deleteNote(noteId: String)
    suspend fun getNoteById(noteId: String): NoteModel?
    suspend fun getAllNotes(): List<NoteModel>
    suspend fun getNotesPaginated(limit: Int, offset: Int): List<NoteModel>
    fun getPagingSource(): PagingSource<Int, NoteEntity>
    suspend fun getUnsyncedNotes(): List<NoteModel>
    suspend fun clearAll()
}