package com.notescollection.app.notes.data.request

import kotlinx.serialization.Serializable

@Serializable
data class NotesResponse(
    val notes: List<NoteResponse>,
    val total: Int
)