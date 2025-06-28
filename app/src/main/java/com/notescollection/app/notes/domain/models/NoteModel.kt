package com.notescollection.app.notes.domain.models

data class NoteModel(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: String,
    val lastEditedAt: String,
    val isSyn: Boolean,
    val isDeleted: Boolean = false
)