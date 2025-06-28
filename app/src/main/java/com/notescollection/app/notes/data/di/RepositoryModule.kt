package com.notescollection.app.notes.data.di

import com.notescollection.app.notes.data.repositoryImpl.AuthRepositoryImpl
import com.notescollection.app.notes.data.repositoryImpl.NotesRepositoryImpl
import com.notescollection.app.notes.domain.repository.AuthRepository
import com.notescollection.app.notes.domain.repository.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindNotesRepository(impl: NotesRepositoryImpl): NotesRepository

}