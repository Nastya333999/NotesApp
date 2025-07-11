package com.notescollection.app.notes.presentation.create_note

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notescollection.app.R
import com.notescollection.app.notes.core.presentation.utils.LoadingUiState
import com.notescollection.app.notes.core.presentation.utils.updateLoadedState
import com.notescollection.app.notes.domain.models.NoteModel
import com.notescollection.app.notes.domain.models.ResultWrapper
import com.notescollection.app.notes.domain.repository.NotesRepository
import com.notescollection.app.notes.presentation.noteList.models.NoteUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.notescollection.app.notes.presentation.noteList.models.toUiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.OffsetDateTime

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val noteId: String? = savedStateHandle[NODE_ID]

    private val _state = MutableStateFlow<LoadingUiState<NoteUiState>>(LoadingUiState.Loading)
    val state: StateFlow<LoadingUiState<NoteUiState>> = _state

    private val _events = Channel<CreateNoteEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        if (noteId == null) newNote() else loadNote(noteId)
    }

    fun onAction(action: CreateNoteAction) {
        val st = _state.value
        if (st !is LoadingUiState.Loaded) return

        when (action) {
            is CreateNoteAction.OnTitleChange -> update {
                it.copy(title = action.title.text)
            }

            is CreateNoteAction.OnDescriptionChange -> update {
                it.copy(description = action.description.text)
            }

            is CreateNoteAction.OnModeChange -> _state.updateLoadedState { state ->
                state.copy(noteMode = action.mode)
            }

            is CreateNoteAction.OnSaveClick -> save()

            is CreateNoteAction.OnCancelClick -> {
                send(CreateNoteEvent.OnCancelClick)
            }
        }
    }

    private inline fun update(block: (NoteUiModel) -> NoteUiModel) {
        _state.updateLoadedState { state ->
            state.copy(note = block(state.note))
        }
    }

    private fun newNote() {
        _state.value = LoadingUiState.Loaded(
            NoteUiState(
                note = NoteUiModel(
                    id = "",
                    date = "",
                    title = context.getString(R.string.title_label),
                    description = "",
                    createdAt = OffsetDateTime.now().toString(),
                    lastEditedAt = OffsetDateTime.now().toString()
                ),
                noteMode = NotesMode.CREATE
            )
        )
    }

    private fun loadNote(id: String) = viewModelScope.launch {
        when (val res = notesRepository.getNoteById(id)) {
            is ResultWrapper.Success -> {
                res.data?.toUiModel(context)?.let { loadedNote ->
                    _state.value = LoadingUiState.Loaded(
                        NoteUiState(
                            note = loadedNote,
                            noteMode = NotesMode.READ
                        )
                    )
                }
            }
            is ResultWrapper.Error -> {
                send(CreateNoteEvent.ShowToast(res.message))
            }
        }
    }

    private fun save() = viewModelScope.launch {
        val loaded = _state.value as? LoadingUiState.Loaded ?: return@launch
        val currentNote = loaded.data.note

        val result = if (currentNote.id.isBlank()) {
            notesRepository.createNote(currentNote.title, currentNote.description)
        } else {
            notesRepository.updateNote(
                NoteModel(
                    id = currentNote.id,
                    title = currentNote.title,
                    content = currentNote.description,
                    createdAt = currentNote.createdAt,
                    lastEditedAt = currentNote.lastEditedAt,
                    isSyn = false
                )
            )
        }

        when (result) {
            is ResultWrapper.Error -> {
                send(CreateNoteEvent.ShowToast(result.message))
            }
            is ResultWrapper.Success<NoteModel> -> {
                val saved = result.data.toUiModel(context)

                _state.updateLoadedState { ui ->
                    ui.copy(
                        note = saved,
                        noteMode = NotesMode.READ
                    )
                }
            }
        }
    }

    private fun send(e: CreateNoteEvent) = viewModelScope.launch { _events.send(e) }

    companion object {
        private const val NODE_ID = "noteId"
    }
}