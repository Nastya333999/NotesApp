package com.notescollection.app.notes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_database")
data class NoteEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val content: String,
    val createdAt: String,
    val lastEditedAt: String,
    val isSyn: Int,
    val isDeleted: Int
)