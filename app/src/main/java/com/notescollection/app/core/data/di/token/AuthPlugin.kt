package com.notescollection.app.core.data.di.token

import com.notescollection.app.core.data.response.AuthResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.request.HttpSendPipeline
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.http.takeFrom
import io.ktor.util.AttributeKey

class AuthPlugin(
    private val tokenStorage: TokenStorage,
    private val baseUrl: String
) : HttpClientPlugin<Unit, AuthPlugin> {

    override val key: AttributeKey<AuthPlugin> = AttributeKey("AuthPlugin")

    override fun prepare(block: Unit.() -> Unit) = this

    override fun install(plugin: AuthPlugin, scope: HttpClient) {
        scope.sendPipeline.intercept(HttpSendPipeline.Monitoring) {
            val originalRequest = context

            tokenStorage.getAccessToken()?.let { token ->
                originalRequest.headers.remove(HttpHeaders.Authorization)
                originalRequest.headers.append(HttpHeaders.Authorization, "Bearer $token")
            }

            val call = proceedWith(originalRequest) as HttpClientCall
            val response = call.response

            if (response.status == HttpStatusCode.Unauthorized) {
                val refresh = tokenStorage.getRefreshToken()
                if (refresh != null) {
                    val refreshResp: HttpResponse = scope.post("$baseUrl/api/auth/refresh") {
                        contentType(ContentType.Application.Json)
                        setBody(mapOf("refreshToken" to refresh))
                    }

                    if (refreshResp.status == HttpStatusCode.OK) {
                        val newTokens = refreshResp.body<AuthResponse>()
                        tokenStorage.saveTokens(newTokens.accessToken, newTokens.refreshToken)

                        val retriedResponse: HttpResponse = scope.request {
                            method = originalRequest.method
                            url.takeFrom(originalRequest.url)
                            headers {
                                appendAll(originalRequest.headers.build())
                                remove(HttpHeaders.Authorization)
                                append(HttpHeaders.Authorization,   "Bearer ${newTokens.accessToken}")
                            }
                            setBody(originalRequest.body)
                        }

                        proceedWith(retriedResponse)
                        return@intercept
                    }
                }
            }

            proceedWith(response)
        }
    }

    companion object : HttpClientPlugin<Unit, AuthPlugin> {

        override val key: AttributeKey<AuthPlugin> = AttributeKey("AuthPlugin")

        override fun prepare(block: Unit.() -> Unit): AuthPlugin =
            error("Create AuthPlugin manually: install(AuthPlugin(tokenStorage, baseUrl))")

        override fun install(plugin: AuthPlugin, scope: HttpClient) {
            plugin.install(plugin, scope)
        }
    }
}