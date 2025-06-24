package com.notescollection.app.notes.data.api

import com.notescollection.app.core.data.request.LoginRequest
import com.notescollection.app.core.data.request.RefreshTokenRequest
import com.notescollection.app.notes.data.request.ErrorResponse
import com.notescollection.app.notes.data.request.NetworkResult
import com.notescollection.app.notes.data.request.RegisterRequest
import com.notescollection.app.notes.data.response.AuthResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class AuthApiImpl(
    private val client: HttpClient
) : AuthApi {

    private val BASE_URL = "https://notemark.pl-coding.com" // TODO() перенести в BuildConfig

    override suspend fun register(
        request: RegisterRequest
    ): NetworkResult<Unit> = try {

        val response: HttpResponse = client.post("/api/auth/register") { // TODO() перенести строки в константы
            header("X-User-Email", "a.dmytriieva.a@gmail.com") // TODO() перенести в BuildConfig
            contentType(ContentType.Application.Json)
            setBody(request)
        }

        if (response.status == HttpStatusCode.OK) {
            NetworkResult.Success(Unit)
        } else {
            val msg = runCatching { response.body<ErrorResponse>().error }
                .getOrElse { response.status.description }
            NetworkResult.Failure(msg)
        }

    } catch (e: Exception) {
        NetworkResult.Failure(e.localizedMessage ?: "Unknown error")
    }

    override suspend fun login(request: LoginRequest): AuthResponse {
        return client.post("${BASE_URL}/api/auth/login") {
            header("X-User-Email", "a.dmytriieva.a@gmail.com")
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