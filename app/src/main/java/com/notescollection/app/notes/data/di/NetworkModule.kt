package com.notescollection.app.notes.data.di

import android.content.Context
import com.notescollection.app.notes.data.api.AuthApi
import com.notescollection.app.notes.data.api.AuthApiImpl
import com.notescollection.app.notes.data.di.token.InMemoryTokenStorage
import com.notescollection.app.notes.data.di.token.TokenStorage
import com.notescollection.app.notes.data.response.AuthResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.isSuccess

@Module
@InstallIn(SingletonComponent::class)
object KtorNetworkModule {

    private const val BASE_URL = "https://notemark.pl-coding.com"

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        explicitNulls = false
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideTokenStorage(@ApplicationContext context: Context): TokenStorage {
        return InMemoryTokenStorage(context)
    }

    @Provides
    @Singleton
    fun provideBaseHttpClient(
        json: Json,
        tokenStorage: TokenStorage
    ): HttpClient = HttpClient(OkHttp) {

        install(ContentNegotiation) {
            json(json)
        }

        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.BODY
        }

        install(Auth) {
            bearer {
                loadTokens {
                    tokenStorage.get()?.let {
                        BearerTokens(it.accessToken, it.refreshToken)
                    }
                }

                refreshTokens {
                    val oldTokens = tokenStorage.get()

                    try {
                        val response = client.post("$BASE_URL/api/auth/refresh") {
                            contentType(ContentType.Application.Json)
                            setBody(mapOf("refreshToken" to (oldTokens?.refreshToken ?: "")))
                            markAsRefreshTokenRequest()
                        }

                        if (response.status.isSuccess()) {
                            val newTokens = response.body<AuthResponse>()
                            tokenStorage.saveTokens(newTokens.accessToken, newTokens.refreshToken)

                            BearerTokens(
                                accessToken = newTokens.accessToken,
                                refreshToken = newTokens.refreshToken
                            )
                        } else {
                            BearerTokens("", "")
                        }
                    } catch (e: Exception) {
                        BearerTokens("", "")
                    }
                }
            }
        }

        defaultRequest {
            url(BASE_URL)
            contentType(ContentType.Application.Json)

            tokenStorage.getUserEmail()?.let { email ->
                header("X-User-Email", email)
            }
        }
    }

    @Provides
    @Singleton
    fun provideAuthApi(client: HttpClient): AuthApi = AuthApiImpl(client)
}
