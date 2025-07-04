package com.notescollection.app.notes.data.di.token

import com.notescollection.app.notes.domain.models.AuthResponseModel

interface TokenStorage {
    fun get(): AuthResponseModel?
    fun saveTokens(accessToken: String, refreshToken: String)
    fun getUserEmail(): String?
    fun saveUserEmail(email: String)

    fun saveUserName(userName: String)
    fun getUserName(): String?
}
