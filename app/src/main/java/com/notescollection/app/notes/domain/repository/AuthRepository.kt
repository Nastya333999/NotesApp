package com.notescollection.app.notes.domain.repository

import com.notescollection.app.notes.domain.models.AuthResponseModel
import com.notescollection.app.notes.domain.models.ResultWrapper

interface AuthRepository {
    suspend fun login(email: String, password: String): ResultWrapper<AuthResponseModel>
}