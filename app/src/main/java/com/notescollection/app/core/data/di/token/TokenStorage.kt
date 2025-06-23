package com.notescollection.app.core.data.di.token

interface TokenStorage {
    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
    suspend fun saveTokens(accessToken: String, refreshToken: String)
}