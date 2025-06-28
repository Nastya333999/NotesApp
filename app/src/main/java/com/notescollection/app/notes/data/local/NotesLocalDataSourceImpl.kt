package com.notescollection.app.notes.data.local

import androidx.paging.PagingSource
import com.notescollection.app.notes.data.database.NoteEntity
import com.notescollection.app.notes.data.database.NotesDao
import com.notescollection.app.notes.domain.models.NoteModel
import com.notescollection.app.notes.domain.models.toEntity
import com.notescollection.app.notes.domain.models.toModel
import javax.inject.Inject

class NotesLocalDataSourceImpl @Inject constructor(
    private val notesDao: NotesDao
) : NotesLocalDataSource {

    override suspend fun insertNote(note: NoteModel) {
        notesDao.insertNote(note.toEntity())
    }

    override suspend fun updateNote(note: NoteModel) {
        notesDao.updateNote(note.toEntity())
    }

    override suspend fun deleteNote(noteId: String) {
        notesDao.getNoteById(noteId)?.let {
            notesDao.deleteNote(it)
        }
    }

    override suspend fun getNoteById(noteId: String): NoteModel? {
        return notesDao.getNoteById(noteId)?.toModel()
    }

    override suspend fun getAllNotes(): List<NoteModel> {
        return notesDao.getAllNotes().map { it.toModel() }
    }

    override suspend fun getNotesPaginated(limit: Int, offset: Int): List<NoteModel> {
        return notesDao.getNotesPaginated(limit, offset).map { it.toModel() }
    }

    override fun getPagingSource(): PagingSource<Int, NoteEntity> {
        return notesDao.getNotesPaging()
    }

    override suspend fun getUnsyncedNotes(): List<NoteModel> {
        return notesDao.getUnsyncedNotes().map { it.toModel() }
    }
}