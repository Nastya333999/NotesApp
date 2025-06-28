package com.notescollection.app.notes.data.repositoryImpl

import com.notescollection.app.notes.data.api.auth.AuthApi
import com.notescollection.app.notes.data.request.LoginRequest
import com.notescollection.app.notes.data.request.RegisterRequest
import com.notescollection.app.notes.data.di.token.TokenStorage
import com.notescollection.app.notes.data.request.NetworkResult
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

    override suspend fun onCreateAccount(
        userName: String,
        email: String,
        password: String
    ): ResultWrapper<Boolean> {

        val registerModel = RegisterRequest(
            username = userName,
            email = email,
            password = password
        )
        return when (val registerRes = authApi.register(registerModel)) {

            is NetworkResult.Failure -> ResultWrapper.Error(
                message = registerRes.message,
                throwable = registerRes.cause
            )

            is NetworkResult.Success -> {
                try {
                    val loginResp = authApi.login(LoginRequest(email, password))

                    tokenStorage.saveTokens(loginResp.accessToken, loginResp.refreshToken)
                    tokenStorage.saveUserEmail(email)
                    tokenStorage.saveUserName(userName)

                    ResultWrapper.Success(true)
                } catch (e: Exception) {

                    ResultWrapper.Error(e.localizedMessage ?: "Unknown error", e)
                }
            }
        }
    }

    override suspend fun getUserFirstNaeLetter(): String {
        val userName: String? = tokenStorage.getUserName()
        return userName?.take(2)?.uppercase() ?: "PL"
    }
}