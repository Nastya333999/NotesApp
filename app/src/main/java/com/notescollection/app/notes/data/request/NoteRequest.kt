package com.notescollection.app.notes.data.request

import kotlinx.serialization.Serializable

@Serializable
data class NoteRequest(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: String,
    val lastEditedAt: String
)