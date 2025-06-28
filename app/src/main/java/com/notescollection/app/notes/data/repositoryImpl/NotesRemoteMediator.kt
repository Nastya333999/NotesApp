package com.notescollection.app.notes.data.repositoryImpl

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.notescollection.app.notes.data.api.notes.NotesApi
import com.notescollection.app.notes.data.database.NoteEntity
import com.notescollection.app.notes.data.database.NotesDao
import com.notescollection.app.notes.data.database.NotesDataBase
import com.notescollection.app.notes.domain.models.toEntity
import com.notescollection.app.notes.domain.models.toModel
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class NotesRemoteMediator(
    private val notesApi: NotesApi,
    private val notesDao: NotesDao,
    private val database: NotesDataBase
) : RemoteMediator<Int, NoteEntity>() {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NoteEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                if (lastItem == null) return MediatorResult.Success(endOfPaginationReached = true)
                state.pages.size
            }
        }

        return try {
            val response = notesApi.getNotes(page, state.config.pageSize)
            val entities = response.notes.map { it.toModel().toEntity() }

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    notesDao.clearAll()
                }
                notesDao.insertAll(entities)
            }

            MediatorResult.Success(endOfPaginationReached = response.notes.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Success(endOfPaginationReached = true)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}