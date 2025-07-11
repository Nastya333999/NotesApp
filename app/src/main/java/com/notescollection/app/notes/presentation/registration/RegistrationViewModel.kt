package com.notescollection.app.notes.presentation.registration

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notescollection.app.R
import com.notescollection.app.notes.domain.models.ResultWrapper
import com.notescollection.app.notes.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegistrationState())
    val state: StateFlow<RegistrationState> = _state

    private val eventChannel = Channel<RegistrationEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: RegistrationAction) {
        when (action) {
            is RegistrationAction.OnUserNameChanged -> updateState(userName = action.value)
            is RegistrationAction.OnEmailChanged -> updateState(email = action.value)
            is RegistrationAction.OnPasswordChanged -> updateState(password = action.value)
            is RegistrationAction.OnRepeatPasswordChanged -> updateState(repeatPassword = action.value)
            is RegistrationAction.OnCreateAccountClick -> {
                val current = _state.value
                viewModelScope.launch {
                    when (val result = authRepository.onCreateAccount(
                        userName = current.userName,
                        email = current.email,
                        password = current.password
                    )) {
                        is ResultWrapper.Error -> {
                            eventChannel.send(RegistrationEvent.ShowToast(result.message))
                        }

                        is ResultWrapper.Success<*> -> {
                            _state.update {
                                it.copy(
                                    toastText = context.getString(R.string.success_account_created),
                                    isToastError = false
                                )
                            }
                            eventChannel.send(RegistrationEvent.OnCreateAccount)
                        }
                    }
                }
            }

            is RegistrationAction.OnLoginClick -> {
                viewModelScope.launch {
                    eventChannel.send(RegistrationEvent.OnLoginClick)
                }
            }

            is RegistrationAction.OnPasswordVisibilityChange -> _state.update {
                it.copy(
                    isPasswordVisible = action.visible
                )
            }

            is RegistrationAction.OnRepeatPasswordVisibilityChange -> _state.update {
                it.copy(
                    isRepeatPasswordVisible = action.visible
                )
            }

            is RegistrationAction.OnToastShown -> {
                _state.update { it.copy(toastText = null) }
            }
        }
    }

    private fun updateState(
        userName: String? = null,
        email: String? = null,
        password: String? = null,
        repeatPassword: String? = null
    ) {
        val cur = _state.value

        val newUserName = userName ?: cur.userName
        val newEmail = email ?: cur.email
        val newPassword = password ?: cur.password
        val newRepeatPassword = repeatPassword ?: cur.repeatPassword

        val isUserNameError = newUserName.length !in 3..20
        val isEmailError = !Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()
        val isPasswordError = !isPasswordValid(newPassword)
        val isRepeatPasswordError = newPassword != newRepeatPassword

        val res = context.resources
        val userNameError = if (isUserNameError && newUserName.isNotBlank())
            res.getString(R.string.error_username_too_short) else null
        val emailError = if (isEmailError && newEmail.isNotBlank())
            res.getString(R.string.error_invalid_email) else null
        val passwordError = if (isPasswordError && newPassword.isNotBlank())
            res.getString(R.string.error_weak_password) else null
        val repeatPasswordError = if (isRepeatPasswordError && newRepeatPassword.isNotBlank())
            res.getString(R.string.error_passwords_do_not_match) else null

        val isButtonEnabled = listOf(
            newUserName, newEmail, newPassword, newRepeatPassword
        ).all { it.isNotBlank() } &&
                !isUserNameError && !isEmailError &&
                !isPasswordError && !isRepeatPasswordError

        _state.value = cur.copy(
            userName = newUserName,
            email = newEmail,
            password = newPassword,
            repeatPassword = newRepeatPassword,

            isUserNameError = isUserNameError,
            isEmailError = isEmailError,
            isPasswordError = isPasswordError,
            isRepeatPasswordError = isRepeatPasswordError,

            userNameError = userNameError,
            emailError = emailError,
            passwordError = passwordError,
            repeatPasswordError = repeatPasswordError,

            isButtonCreateAccountEnabled = isButtonEnabled
        )
    }

    private fun isPasswordValid(password: String): Boolean =
        password.length >= 8 &&
                password.any { it.isLowerCase() } &&
                password.any { it.isUpperCase() } &&
                password.any { it.isDigit() || !it.isLetterOrDigit() }
}