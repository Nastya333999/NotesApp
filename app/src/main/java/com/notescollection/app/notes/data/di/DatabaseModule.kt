package com.notescollection.app.notes.data.di

import android.content.Context
import androidx.room.Room
import com.notescollection.app.notes.data.database.NotesDao
import com.notescollection.app.notes.data.database.NotesDataBase
import com.notescollection.app.notes.data.local.NotesLocalDataSource
import com.notescollection.app.notes.data.local.NotesLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideBuildingDatabase(
        @ApplicationContext context: Context,
    ): NotesDataBase {
        return Room.databaseBuilder(
            context,
            NotesDataBase::class.java,
            NotesDataBase.NAME
        ).fallbackToDestructiveMigration(false).build()
    }

    @Provides
    @Singleton
    fun notesDao(
        database: NotesDataBase,
    ): NotesDao {
        return database.notesDatabaseDao()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        notesDao: NotesDao
    ): NotesLocalDataSource {
        return NotesLocalDataSourceImpl(notesDao)
    }
}
