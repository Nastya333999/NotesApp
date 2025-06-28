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

    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override fun get(): AuthResponseModel? {
        val access = prefs.getString(KEY_ACCESS, null)
        val refresh = prefs.getString(KEY_REFRESH, null)
        return if (access != null && refresh != null) {
            AuthResponseModel(access, refresh)
        } else null
    }

    override fun saveTokens(accessToken: String, refreshToken: String) {
        prefs.edit {
            putString(KEY_ACCESS, accessToken)
            putString(KEY_REFRESH, refreshToken)
        }
    }

    override fun getUserEmail(): String? {
        return prefs.getString(KEY_EMAIL, null)
    }

    override fun saveUserEmail(email: String) {
        prefs.edit { putString(KEY_EMAIL, email) }
    }

    override fun saveUserName(userName: String) {
        prefs.edit { putString(KEY_USERNAME, userName) }
    }

    override fun getUserName(): String? {
        return prefs.getString(KEY_USERNAME, null)
    }

    private companion object {
        const val PREF_NAME = "auth"
        const val KEY_ACCESS = "access"
        const val KEY_REFRESH = "refresh"
        const val KEY_EMAIL = "email"
        const val KEY_USERNAME = "userName"
    }
}