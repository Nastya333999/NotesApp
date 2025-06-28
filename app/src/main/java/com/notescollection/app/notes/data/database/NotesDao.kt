package com.notescollection.app.notes.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes_database")
    suspend fun getAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM notes_database WHERE id = :id")
    suspend fun getNoteById(id: String): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Query("SELECT * FROM notes_database ORDER BY createdAt DESC LIMIT :limit OFFSET :offset")
    suspend fun getNotesPaginated(limit: Int, offset: Int): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notes: List<NoteEntity>)

    @Query("DELETE FROM notes_database")
    suspend fun clearAll()

    @Query("SELECT * FROM notes_database WHERE isDeleted = 0 ORDER BY createdAt DESC")
    fun getNotesPaging(): PagingSource<Int, NoteEntity>

    @Query("SELECT * FROM notes_database WHERE isSyn = 0")
    suspend fun getUnsyncedNotes(): List<NoteEntity>

}