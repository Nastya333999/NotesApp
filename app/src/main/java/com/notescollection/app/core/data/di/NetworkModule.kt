package com.notescollection.app.core.data.di

import com.notescollection.app.core.data.api.AuthApi
import com.notescollection.app.core.data.api.AuthApiImpl
import com.notescollection.app.core.data.di.token.AuthPlugin
import com.notescollection.app.core.data.di.token.InMemoryTokenStorage
import com.notescollection.app.core.data.di.token.TokenStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
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
    fun provideTokenStorage(): TokenStorage = InMemoryTokenStorage()

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
            level  = LogLevel.BODY
        }

        install(AuthPlugin(tokenStorage, BASE_URL))

        defaultRequest {
            url(BASE_URL)
            contentType(ContentType.Application.Json)
        }
    }

    @Provides
    @Singleton
    fun provideAuthApi(client: HttpClient): AuthApi = AuthApiImpl(client)
}
