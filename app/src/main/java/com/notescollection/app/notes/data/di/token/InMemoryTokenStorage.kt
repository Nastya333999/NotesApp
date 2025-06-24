package com.notescollection.app.notes.data.di.token

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit
import com.notescollection.app.notes.domain.models.AuthResponseModel

@Singleton
class InMemoryTokenStorage @Inject constructor(
    private val context: Context
) : TokenStorage {

    private val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    override fun get(): AuthResponseModel? {
        val access = prefs.getString("access", null)
        val refresh = prefs.getString("refresh", null)
        return if (access != null && refresh != null) {
            AuthResponseModel(access, refresh)
        } else null
    }

    override fun saveTokens(accessToken: String, refreshToken: String) {
        prefs.edit {
            putString("access", accessToken)
                .putString("refresh", refreshToken)
        }
    }

    override fun getUserEmail(): String? {
        return prefs.getString("email", null)
    }

    override fun saveUserEmail(email: String) {
        prefs.edit { putString("email", email) }
    }
}