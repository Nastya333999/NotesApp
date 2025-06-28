package com.notescollection.app.notes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NotesDataBase : RoomDatabase() {
    abstract fun notesDatabaseDao(): NotesDao

    companion object {
        const val NAME = "notes_database.db"
    }
}