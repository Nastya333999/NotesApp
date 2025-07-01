package com.notescollection.app.notes.presentation.noteList

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.notescollection.app.notes.domain.models.ResultWrapper
import com.notescollection.app.notes.domain.repository.AuthRepository
import com.notescollection.app.notes.domain.repository.NotesRepository
import com.notescollection.app.notes.presentation.noteList.models.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val notesRepository: NotesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(NoteListState())
    val state: StateFlow<NoteListState> = _state

    private val eventChannel = Channel<NoteListEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch(Dispatchers.IO) {
            val userInitials = authRepository.getUserFirstNaeLetter()
            _state.update {
                it.copy(
                    userName = userInitials,
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getNotes() {
        viewModelScope.launch(Dispatchers.IO) {

            _state.update {
                it.copy(
                    notesFlow = notesRepository.getPagedNotes()
                        .cachedIn(viewModelScope)
                        .map { pagingData ->
                            pagingData
                                .map { it.toUiModel() }
                                .filter { note -> !note.isDeleted }
                        }
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onAction(action: NoteListAction) {
        when (action) {
            NoteListAction.OnNewNoteCreate -> {
                viewModelScope.launch {
                    eventChannel.send(NoteListEvent.OnNewNoteEvent)
                }
            }

            is NoteListAction.OnDeleteNote -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val result = notesRepository.deleteNote(action.note.id)
                    when (result) {
                        is ResultWrapper.Error -> {}
                        is ResultWrapper.Success<*> -> {
                            _state.update {
                                it.copy(
                                    notesFlow = notesRepository.getPagedNotes()
                                        .cachedIn(viewModelScope)
                                        .map { pagingData ->
                                            pagingData
                                                .map { it.toUiModel() }
                                                .filter { note -> !note.isDeleted }
                                        }
                                )
                            }
                        }
                    }
                }
            }

            is NoteListAction.OnNoteClick -> {
                viewModelScope.launch {
                    eventChannel.send(NoteListEvent.OnNoteCLick(action.note))
                }
            }

            is NoteListAction.OnSettingsClick -> {
                viewModelScope.launch {
                    eventChannel.send(NoteListEvent.OnSettingsEvent)
                }
            }
        }
    }
}