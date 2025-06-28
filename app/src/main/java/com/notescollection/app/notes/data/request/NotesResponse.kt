package com.notescollection.app.notes.data.request

import kotlinx.serialization.Serializable

@Serializable
data class NotesResponse(
    val notes: List<NoteResponse> = emptyList(),
    val total: Int = 0
)