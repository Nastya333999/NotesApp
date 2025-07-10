package com.notescollection.app.notes.presentation.create_note

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notescollection.app.R
import com.notescollection.app.notes.domain.models.NoteModel
import com.notescollection.app.notes.domain.models.ResultWrapper
import com.notescollection.app.notes.domain.repository.NotesRepository
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

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val noteId: String? = savedStateHandle[NODE_ID]

    private val _state = MutableStateFlow(CreateNoteState())
    val state: StateFlow<CreateNoteState> = _state

    private val _eventChannel = Channel<CreateNoteEvent>()
    val events = _eventChannel.receiveAsFlow()

    init {
        if (noteId == null) {
            initNote()
        } else {
            loadNote(noteId)
        }
    }

    fun onAction(action: CreateNoteAction) {
        when (action) {
            is CreateNoteAction.OnCancelClick -> {
                viewModelScope.launch {
                    _eventChannel.send(CreateNoteEvent.OnCancelClick)
                }
            }

            is CreateNoteAction.OnSaveClick -> onSaveClick()

            is CreateNoteAction.OnDescriptionChange -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(description = action.description)
                    }
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

            is CreateNoteAction.OnModeChange -> {
                viewModelScope.launch {
                    _state.update { state ->
                        state.copy(
                            noteMode = action.mode
                        )
                    }
                }
            }
        }
    }

    private fun onSaveClick() {
        val currentState = _state.value
        val title = currentState.title.text

        if (currentState.noteForChange == null) {
            createNote(
                title = title,
                description = currentState.description.text
            )
            Log.e("sdsddfgv", "${_state.value}")
        } else {
            updateNote(
                noteModel = NoteModel(
                    id = currentState.noteForChange.id,
                    title = title,
                    content = currentState.description.text,
                    createdAt = currentState.noteForChange.createdAt,
                    lastEditedAt = currentState.noteForChange.lastEditedAt,
                    isSyn = false
                )
            )
        }
    }

    private fun updateNote(noteModel: NoteModel) {
        viewModelScope.launch {
            val result = notesRepository.updateNote(note = noteModel)
            when (result) {
                is ResultWrapper.Error -> {}
                is ResultWrapper.Success<*> -> {
                    viewModelScope.launch {
                        _state.update { state ->
                            state.copy(noteMode = NotesMode.READ)
                        }
                    }
                }
            }
        }
    }

    private fun createNote(title: String, description: String) {
        viewModelScope.launch {
            val result = notesRepository.createNote(
                title = title,
                description = description,
            )
            when (result) {
                is ResultWrapper.Error -> {}
                is ResultWrapper.Success<NoteModel> -> {
                    viewModelScope.launch {
                        _state.update { state ->
                            state.copy(
                                noteMode = NotesMode.READ,
                                noteForChange = result.data.toUiModel(context)
                            )
                        }
                    }
                }
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
                        val uiModel = noteModel.toUiModel(context)
                        _state.update {
                            it.copy(
                                noteForChange = uiModel,
                                title = TextFieldValue(
                                    text = uiModel.title,
                                    selection = TextRange(uiModel.title.length),
                                ),
                                description = TextFieldValue(
                                    text = uiModel.description,
                                    selection = TextRange(uiModel.description.length),
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun initNote() {
        val defaultTitle = context.getString(R.string.title_label)
        _state.value = CreateNoteState(
            title = TextFieldValue(
                text = defaultTitle,
                selection = TextRange(defaultTitle.length)
            ),
            description = TextFieldValue(
                text = "",
                selection = TextRange(defaultTitle.length)
            ),
            noteMode = NotesMode.CREATE
        )
    }

    companion object {
        private const val NODE_ID = "noteId"
    }
}