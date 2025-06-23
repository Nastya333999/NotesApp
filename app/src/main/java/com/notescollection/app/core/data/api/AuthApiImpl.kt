package com.notescollection.app.core.data.api

import com.notescollection.app.core.data.request.LoginRequest
import com.notescollection.app.core.data.request.RefreshTokenRequest
import com.notescollection.app.core.data.request.RegisterRequest
import com.notescollection.app.core.data.response.AuthResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthApiImpl(
    private val client: HttpClient
) : AuthApi {

    override suspend fun register(request: RegisterRequest) {
        client.post("/api/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    override suspend fun login(request: LoginRequest): AuthResponse {
        return client.post("/api/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    override suspend fun refreshToken(request: RefreshTokenRequest): AuthResponse {
        return client.post("/api/auth/refresh") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}