package com.notescollection.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notescollection.app.notes.data.di.token.TokenStorage
import com.notescollection.app.notes.domain.repository.NotesRepository
import com.notescollection.app.notes.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.delay

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tokenStorage: TokenStorage,
    private val networkMonitor: NetworkMonitor,
    private val repository: NotesRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    private val _isConnected = MutableStateFlow(false)
    private var retryJob: Job? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val token = tokenStorage.get()
            _state.update {
                it.copy(startDestination = if (token != null) Screens.NoteListScreen else Screens.SplashScreen)
            }
        }
        observeConnectivity()
    }

    private fun observeConnectivity() {
        viewModelScope.launch {
            networkMonitor.connectivityFlow.collectLatest { connected ->
                _isConnected.value = connected

                if (connected) {
                    repository.syncUnsyncedNotes()
                } else {
                    retrySyncEvery30sUntilConnected()
                }
            }
        }
    }

    private fun retrySyncEvery30sUntilConnected() {
        retryJob?.cancel()
        retryJob = viewModelScope.launch {
            while (true) {
                delay(30_000)
                if (networkMonitor.isCurrentlyConnected()) {
                    _isConnected.value = true
                    repository.syncUnsyncedNotes()
                    break
                }
            }
        }
    }

}

data class MainState(
    val startDestination: Screens = Screens.SplashScreen
)