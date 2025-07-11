package com.notescollection.app.notes.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notescollection.app.notes.domain.models.ResultWrapper
import com.notescollection.app.notes.domain.repository.AuthRepository
import com.notescollection.app.notes.domain.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val notesRepository: NotesRepository
) : ViewModel() {

    private val eventChannel = Channel<SettingsEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.OnLogoutClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val result = authRepository.logOut()
                    when (result) {
                        is ResultWrapper.Error -> {
                            eventChannel.send(SettingsEvent.ShowToast(result.message))
                        }
                        is ResultWrapper.Success<*> -> {
                            notesRepository.deleteAllNotes()
                            eventChannel.send(SettingsEvent.OnBackEvent)
                        }
                    }
                }
            }

            is SettingsAction.OnBackClick -> {
                viewModelScope.launch {
                    eventChannel.send(SettingsEvent.OnLogoutEvent)
                }
            }
        }
    }
}