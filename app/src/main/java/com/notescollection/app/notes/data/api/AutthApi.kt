package com.notescollection.app.notes.data.api

import com.notescollection.app.core.data.request.LoginRequest
import com.notescollection.app.core.data.request.RefreshTokenRequest
import com.notescollection.app.core.data.request.RegisterRequest
import com.notescollection.app.notes.data.response.AuthResponse

interface AuthApi {
    suspend fun register(request: RegisterRequest)
    suspend fun login(request: LoginRequest): AuthResponse
    suspend fun refreshToken(request: RefreshTokenRequest): AuthResponse
}