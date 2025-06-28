package com.notescollection.app.notes.presentation.create_note

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notescollection.app.R
import com.notescollection.app.notes.domain.models.NoteModel
import com.notescollection.app.notes.domain.models.ResultWrapper
import com.notescollection.app.notes.domain.repository.NotesRepository
import com.notescollection.app.notes.presentation.noteList.models.NoteUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.notescollection.app.notes.presentation.noteList.models.toUiModel
import dagger.hilt.android.qualifiers.ApplicationContext

@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow(CreateNoteState())
    val state: StateFlow<CreateNoteState> = _state

    private val _eventChannel = Channel<CreateNoteEvent>()
    val events = _eventChannel.receiveAsFlow()

    fun onAction(action: CreateNoteAction) {
        when (action) {
            is CreateNoteAction.OnCancelClick -> {
                viewModelScope.launch {
                    if (state.value.noteForChange == null) {
                        _eventChannel.send(CreateNoteEvent.OnCancelClick)
                    }
                }
            }

            is CreateNoteAction.OnSaveClick -> {
                val currentState = _state.value
                val title = currentState.title.text
                if (currentState.noteForChange == null) {
                    createNote(title, currentState.description)
                } else {
                    updateNote(
                        noteModel = NoteModel(
                            id = currentState.noteForChange.id,
                            title = title,
                            content = currentState.description,
                            createdAt = currentState.noteForChange.createdAt,
                            lastEditedAt = currentState.noteForChange.lastEditedAt,
                            isSyn = false
                        )
                    )
                }
            }

            is CreateNoteAction.OnDescriptionChange -> {
                _state.update {
                    it.copy(description = action.description)
                }
            }

            is CreateNoteAction.OnTitleChange -> {
                _state.update {
                    it.copy(title = action.title)
                }
            }

            CreateNoteAction.NavigateBack -> {
                viewModelScope.launch {
                    _eventChannel.send(CreateNoteEvent.OnCancelClick)
                }
            }
        }
    }

    private fun updateNote(noteModel: NoteModel) {
        viewModelScope.launch {
            val result = notesRepository.updateNote(note = noteModel)
            when (result) {
                is ResultWrapper.Error -> {}
                is ResultWrapper.Success<*> -> _eventChannel.send(CreateNoteEvent.OnCancelClick)
            }
        }
    }

    fun createNote(title: String, description: String) {
        viewModelScope.launch {
            val result = notesRepository.createNote(
                title = title,
                description = description,
            )
            when (result) {
                is ResultWrapper.Error -> {}
                is ResultWrapper.Success<*> -> _eventChannel.send(CreateNoteEvent.OnCancelClick)
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadNote(noteId: String) {
        viewModelScope.launch {
            val result = notesRepository.getNoteById(noteId = noteId)
            when (result) {
                is ResultWrapper.Error -> {}
                is ResultWrapper.Success -> {
                    val noteModel = result.data
                    if (noteModel != null) {
                        val uiModel = noteModel.toUiModel()
                        _state.update {
                            it.copy(
                                noteForChange = uiModel,
                                title = TextFieldValue(
                                    text = uiModel.title,
                                    selection = TextRange(uiModel.title.length)
                                ),
                                description = uiModel.description
                            )
                        }
                    }
                }
            }
        }
    }

    fun init(noteForChange: NoteUiModel?) {
        if (noteForChange != null) {
            _state.value = CreateNoteState(
                title = TextFieldValue(
                    text = noteForChange.title,
                    selection = TextRange(noteForChange.title.length)
                ),
                description = noteForChange.description,
                noteForChange = noteForChange
            )
        } else {
            val defaultTitle = context.getString(R.string.title_label)
            _state.value = CreateNoteState(
                title = TextFieldValue(
                    text = defaultTitle,
                    selection = TextRange(defaultTitle.length)
                ),
                description = ""
            )
        }
    }
}