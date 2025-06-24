package com.notescollection.app.notes.data.api

import com.notescollection.app.core.data.request.LoginRequest
import com.notescollection.app.core.data.request.RefreshTokenRequest
import com.notescollection.app.core.data.request.RegisterRequest
import com.notescollection.app.notes.data.response.AuthResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthApiImpl(
    private val client: HttpClient
) : AuthApi {

    private val BASE_URL = "https://notemark.pl-coding.com"

    override suspend fun register(request: RegisterRequest) {
        client.post("/api/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    override suspend fun login(request: LoginRequest): AuthResponse {
        return client.post("${BASE_URL}/api/auth/login") {
            header("X-User-Email", request.email)
            header("Debug", "true")
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