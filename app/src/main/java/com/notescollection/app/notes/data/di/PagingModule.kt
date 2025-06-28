package com.notescollection.app.notes.data.di

import com.notescollection.app.notes.data.api.notes.NotesApi
import com.notescollection.app.notes.data.database.NotesDao
import com.notescollection.app.notes.data.database.NotesDataBase
import com.notescollection.app.notes.data.repositoryImpl.NotesRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PagingModule {

    @Provides
    fun provideNotesRemoteMediator(
        notesApi: NotesApi,
        notesDao: NotesDao,
        database: NotesDataBase
    ): NotesRemoteMediator {
        return NotesRemoteMediator(notesApi, notesDao, database)
    }
}