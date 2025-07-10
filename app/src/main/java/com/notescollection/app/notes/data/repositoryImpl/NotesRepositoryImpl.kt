package com.notescollection.app.notes.data.repositoryImpl

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.notescollection.app.notes.data.api.notes.NotesApi
import com.notescollection.app.notes.data.di.ApplicationScope
import com.notescollection.app.notes.data.local.NotesLocalDataSource
import com.notescollection.app.notes.domain.models.NoteModel
import com.notescollection.app.notes.domain.models.ResultWrapper
import com.notescollection.app.notes.domain.models.toModel
import com.notescollection.app.notes.domain.repository.NotesRepository
import javax.inject.Inject
import com.notescollection.app.notes.domain.models.toRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.UUID

class NotesRepositoryImpl @Inject constructor(
    private val notesApi: NotesApi,
    private val notesRemoteMediator: NotesRemoteMediator,
    private val notesLocalDataSource: NotesLocalDataSource,
    @ApplicationScope private val appScope: CoroutineScope
) : NotesRepository {

    override suspend fun syncUnsyncedNotes(): ResultWrapper<Unit> {
        appScope.launch {
            val unsyncedNotes = notesLocalDataSource.getUnsyncedNotes()

            var hasError = false
            var lastError: Exception? = null

            for (note in unsyncedNotes) {
                try {
                    when {
                        note.isDeleted -> {
                            notesApi.deleteNote(note.id)
                            notesLocalDataSource.deleteNote(note.id)
                        }

                        else -> {
                            notesApi.createNote(note.toRequest())
                            notesLocalDataSource.updateNote(note.copy(isSyn = true))
                        }
                    }
                } catch (e: Exception) {
                    hasError = true
                    lastError = e
                }
            }
        }
        return ResultWrapper.Success(Unit)
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagedNotes(): Flow<PagingData<NoteModel>> {
        return Pager(
            config = PagingConfig(pageSize = 50),
            remoteMediator = notesRemoteMediator,
            pagingSourceFactory = {
                notesLocalDataSource.getPagingSource()
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toModel() }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createNote(title: String, description: String): ResultWrapper<NoteModel> {
        val now = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
        val note = NoteModel(
            id = UUID.randomUUID().toString(),
            title = title,
            content = description,
            createdAt = now,
            lastEditedAt = now,
            isSyn = false,
            isDeleted = false
        )

        notesLocalDataSource.insertNote(note)

        return appScope.async {
            try {
                val result = notesApi.createNote(note.toRequest())
                notesLocalDataSource.updateNote(note.copy(isSyn = true))
                ResultWrapper.Success(result.toModel())
            } catch (e: IOException) {
                notesLocalDataSource.insertNote(note.copy(isSyn = false))
                ResultWrapper.Success(note)
            } catch (e: Exception) {
                ResultWrapper.Error(e.localizedMessage ?: "Unknown error", e)
            }
        }.await()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun updateNote(note: NoteModel): ResultWrapper<NoteModel> = try {
        val updatedAt = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
        val noteForUpdate = note.copy(lastEditedAt = updatedAt)

        val result = notesApi.updateNote(noteForUpdate.toRequest())
        notesLocalDataSource.updateNote(noteForUpdate.copy(isSyn = true))
        ResultWrapper.Success(result.toModel())
    } catch (e: Exception) {
        val updatedNote = note.copy(
            lastEditedAt = DateTimeFormatter.ISO_INSTANT.format(Instant.now()),
            isSyn = false
        )
        notesLocalDataSource.updateNote(updatedNote)

        ResultWrapper.Error(e.localizedMessage ?: "Unknown error", e)
    }

    override suspend fun deleteNote(noteId: String): ResultWrapper<Unit> {
        return try {
            notesApi.deleteNote(noteId)
            notesLocalDataSource.deleteNote(noteId)
            ResultWrapper.Success(Unit)
        } catch (e: Exception) {
            val note = notesLocalDataSource.getNoteById(noteId)
            return if (note != null) {
                notesLocalDataSource.updateNote(
                    note.copy(
                        isDeleted = true,
                        isSyn = false
                    )
                )
                ResultWrapper.Success(Unit)
            } else {
                ResultWrapper.Error("Note not found locally", e)
            }
        }
    }

    override suspend fun getNoteById(noteId: String): ResultWrapper<NoteModel?> = try {
        val note = notesLocalDataSource.getNoteById(noteId)
        ResultWrapper.Success(note)
    } catch (e: Exception) {
        ResultWrapper.Error(e.localizedMessage ?: "Unknown error", e)
    }

    override suspend fun deleteAllNotes() {
        notesLocalDataSource.clearAll()
    }
}