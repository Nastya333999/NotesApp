package com.notescollection.app.core.data.di

import com.notescollection.app.notes.data.repositoryImpl.AuthRepositoryImpl
import com.notescollection.app.notes.domain.repository.AuthRepository
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
    fun bindRecipeRepository(impl: AuthRepositoryImpl): AuthRepository
}