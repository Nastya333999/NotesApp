package com.notescollection.app.notes.data.repositoryImpl

import com.notescollection.app.notes.data.api.AuthApi
import com.notescollection.app.core.data.request.LoginRequest
import com.notescollection.app.notes.data.di.token.TokenStorage
import com.notescollection.app.notes.domain.models.AuthResponseModel
import com.notescollection.app.notes.domain.models.ResultWrapper
import com.notescollection.app.notes.domain.models.toModel
import com.notescollection.app.notes.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val tokenStorage: TokenStorage
) : AuthRepository {

    override suspend fun login(email: String, password: String): ResultWrapper<AuthResponseModel> {
        return try {
            val response = authApi.login(LoginRequest(email, password))

            tokenStorage.saveTokens(response.accessToken, response.refreshToken)
            tokenStorage.saveUserEmail(email)

            ResultWrapper.Success(response.toModel())
        } catch (e: Exception) {
            ResultWrapper.Error(e.localizedMessage ?: "Unknown error", e)
        }
    }
}