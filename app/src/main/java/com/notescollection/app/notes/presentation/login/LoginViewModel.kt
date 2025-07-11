package com.notescollection.app.notes.presentation.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notescollection.app.R
import com.notescollection.app.notes.core.presentation.utils.ConnectivityObserver
import com.notescollection.app.notes.core.presentation.utils.ConnectivityStatus
import com.notescollection.app.notes.domain.models.ResultWrapper
import com.notescollection.app.notes.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    connectivityObserver: ConnectivityObserver,
    @ApplicationContext private val context: Context,
) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState())
    val state: StateFlow<LoginState> = _state

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()

    private var lastConnectivityStatus: ConnectivityStatus? = null

    init {
        viewModelScope.launch {
            connectivityObserver.observe().collect { status ->
                if (lastConnectivityStatus == null && status == ConnectivityStatus.Unavailable) {
                    eventChannel.send(LoginEvent.ShowToast(context.getString(R.string.no_internet)))
                }

                if (lastConnectivityStatus != null && status != lastConnectivityStatus) {
                    when (status) {
                        ConnectivityStatus.Available ->
                            eventChannel.send(LoginEvent.ShowToast(context.getString(R.string.internet_connected)))

                        ConnectivityStatus.Unavailable ->
                            eventChannel.send(LoginEvent.ShowToast(context.getString(R.string.no_internet)))
                    }
                }

                lastConnectivityStatus = status
            }
        }
    }

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
                            eventChannel.send(LoginEvent.OnLoginClick)
                        }

                        is ResultWrapper.Error -> {
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