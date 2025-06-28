package com.notescollection.app.notes.domain.models

import com.notescollection.app.notes.data.database.NoteEntity
import com.notescollection.app.notes.data.request.NoteRequest
import com.notescollection.app.notes.data.request.NoteResponse

fun NoteModel.toRequest(): NoteRequest = NoteRequest(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    lastEditedAt = lastEditedAt
)

fun NoteResponse.toModel(): NoteModel = NoteModel(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    lastEditedAt = lastEditedAt,
    isSyn = true
)

fun NoteModel.toEntity(): NoteEntity = NoteEntity(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    lastEditedAt = lastEditedAt,
    isSyn = if (isSyn) 1 else 0,
    isDeleted = if (isDeleted) 1 else 0
)

fun NoteEntity.toModel(): NoteModel = NoteModel(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    lastEditedAt = lastEditedAt,
    isSyn = isSyn == 1,
    isDeleted = isDeleted == 1
)