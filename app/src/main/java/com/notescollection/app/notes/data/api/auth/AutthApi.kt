package com.notescollection.app.notes.data.api.auth

import com.notescollection.app.notes.data.request.LoginRequest
import com.notescollection.app.core.data.request.RefreshTokenRequest
import com.notescollection.app.notes.data.request.NetworkResult
import com.notescollection.app.notes.data.request.RegisterRequest
import com.notescollection.app.notes.data.response.AuthResponse

interface AuthApi {
    suspend fun register(request: RegisterRequest): NetworkResult<Unit>
    suspend fun login(request: LoginRequest): AuthResponse
    suspend fun refreshToken(request: RefreshTokenRequest): AuthResponse
    suspend fun logOut(refreshToken: String): NetworkResult<Unit>
}