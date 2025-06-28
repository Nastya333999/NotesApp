package com.notescollection.app.notes.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notescollection.app.notes.domain.models.ResultWrapper
import com.notescollection.app.notes.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(LoginState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = LoginState()
        )

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnEmailChange -> {
                _state.update { it.copy(email = action.email, isError = false) }
            }

            is LoginAction.OnPasswordChange -> {
                _state.update { it.copy(password = action.password, isError = false) }
            }

            is LoginAction.OnLoginClick -> {
                viewModelScope.launch {
                    val current = _state.value
                    val hasError = current.email.isBlank() || current.password.isBlank()

                    if (hasError) {
                        _state.update { it.copy(isError = true) }
                        return@launch
                    }

                    _state.update { it.copy(isError = false) }

                    when (val result = authRepository.login(current.email, current.password)) {
                        is ResultWrapper.Success -> {
                            Log.d("LoginViewModeleeee", "Success ")
                            eventChannel.send(LoginEvent.OnLoginClick)
                        }

                        is ResultWrapper.Error -> {
                            Log.d("LoginViewModeleeee", "ERROR ${result.message}")

                            _state.update {
                                it.copy(
                                    isError = true,
                                    errorMessage = result.message
                                )
                            }
                        }
                    }
                }
            }

            is LoginAction.OnRegisterClick -> {
                viewModelScope.launch {
                    eventChannel.send(LoginEvent.OnRegisterClick)
                }
            }

            is LoginAction.OnPasswordVisibilityChange -> {
                _state.update { it.copy(isPasswordVisible = action.visible) }
            }
        }
    }
}