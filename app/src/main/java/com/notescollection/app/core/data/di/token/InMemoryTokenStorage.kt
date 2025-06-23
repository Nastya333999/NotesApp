package com.notescollection.app.core.data.di.token

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryTokenStorage @Inject constructor() : TokenStorage {
    private var accessToken: String? = null
    private var refreshToken: String? = null

    override suspend fun getAccessToken(): String? = accessToken
    override suspend fun getRefreshToken(): String? = refreshToken
    override suspend fun saveTokens(accessToken: String, refreshToken: String) {
        this.accessToken = accessToken
        this.refreshToken = refreshToken
    }
}