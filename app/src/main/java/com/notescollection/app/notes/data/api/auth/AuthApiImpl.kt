package com.notescollection.app.notes.data.api.auth

import com.notescollection.app.notes.data.request.LoginRequest
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

    override suspend fun register(
        request: RegisterRequest
    ): NetworkResult<Unit> = try {

        val response: HttpResponse =
            client.post(REGISTER_PATH) {
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
        NetworkResult.Failure(e.localizedMessage ?: ERROR_UNKNOWN)
    }

    override suspend fun login(request: LoginRequest): AuthResponse {
        return client.post(BASE_URL + LOGIN_PATH) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    override suspend fun refreshToken(request: RefreshTokenRequest): AuthResponse {
        return client.post(REFRESH_TOKEN_PATH) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    companion object {
        private const val BASE_URL = "https://notemark.pl-coding.com"
        private const val REGISTER_PATH = "/api/auth/register"
        private const val LOGIN_PATH = "/api/auth/login"
        private const val REFRESH_TOKEN_PATH = "/api/auth/refresh"
        private const val X_USER_EMAIL = "X-User-Email"
        private const val MOCK_EMAIL = "a.dmytriieva.a@gmail.com"
        private const val ERROR_UNKNOWN = "Unknown error"
    }
}