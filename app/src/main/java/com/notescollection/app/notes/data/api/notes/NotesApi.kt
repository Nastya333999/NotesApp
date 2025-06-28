package com.notescollection.app.notes.data.api.notes

import com.notescollection.app.notes.data.request.NoteRequest
import com.notescollection.app.notes.data.request.NoteResponse
import com.notescollection.app.notes.data.request.NotesResponse

interface NotesApi {
    suspend fun createNote(request: NoteRequest): NoteResponse
    suspend fun updateNote(request: NoteRequest): NoteResponse
    suspend fun getNotes(page: Int = -1, size: Int = 20): NotesResponse
    suspend fun deleteNote(id: String): Boolean
}