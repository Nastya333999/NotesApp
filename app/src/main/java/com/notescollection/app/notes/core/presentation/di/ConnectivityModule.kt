package com.notescollection.app.notes.core.presentation.di

import com.notescollection.app.notes.core.presentation.utils.ConnectivityObserver
import com.notescollection.app.notes.core.presentation.utils.NetworkConnectivityObserver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectivityModule {

    @Binds
    abstract fun bindConnectivityObserver(
        impl: NetworkConnectivityObserver
    ): ConnectivityObserver
}