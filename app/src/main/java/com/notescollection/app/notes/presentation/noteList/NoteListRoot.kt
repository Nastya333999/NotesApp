package com.notescollection.app.notes.presentation.noteList

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.notescollection.app.notes.core.presentation.designsystem.components.StatusBarStyle
import com.notescollection.app.notes.core.presentation.designsystem.theme.NotesAppTheme
import com.notescollection.app.notes.core.presentation.utils.DeviceConfiguration
import com.notescollection.app.notes.core.presentation.utils.LoadingUiState
import com.notescollection.app.notes.core.presentation.utils.ObserveAsEvents
import com.notescollection.app.notes.core.presentation.utils.ScreenSizesPreview
import com.notescollection.app.notes.presentation.create_note.NoteUiState
import com.notescollection.app.notes.presentation.noteList.components.LandscapeOrientationNotesListScreen
import com.notescollection.app.notes.presentation.noteList.components.PortraitNoteListScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteListRoot(
    navigateToCreateNote: () -> Unit,
    onNoteDetailsClick: (String) -> Unit,
    navigateToSettings: () -> Unit,
) {
    val viewModel: NoteListViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getNotes()
    }
    ObserveAsEvents(viewModel.events) { event: NoteListEvent ->
        when (event) {
            is NoteListEvent.OnNewNoteEvent -> {
                navigateToCreateNote()
            }

            is NoteListEvent.OnNoteCLick -> {
                onNoteDetailsClick(event.note.id)
            }

            is NoteListEvent.OnSettingsEvent -> {
                navigateToSettings()
            }
        }
    }

    when (state) {
        is LoadingUiState.Loaded<NoteListState> ->
            NoteListScreen(
                state = (state as LoadingUiState.Loaded<NoteListState>).data,
                onAction = viewModel::onAction
            )

        is LoadingUiState.Loading -> Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}

@SuppressLint("ContextCastToActivity")
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun NoteListScreen(
    state: NoteListState,
    onAction: (NoteListAction) -> Unit,
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(MaterialTheme.colorScheme.primary),
    ) {
        StatusBarStyle()
        val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
        val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
        when (deviceConfiguration) {
            DeviceConfiguration.MOBILE_PORTRAIT -> {
                PortraitNoteListScreen(
                    state = state,
                    onAction = onAction
                )
            }

            DeviceConfiguration.MOBILE_LANDSCAPE -> {
                LandscapeOrientationNotesListScreen(
                    state = state,
                    onAction = onAction
                )
            }

            DeviceConfiguration.TABLET_PORTRAIT -> {
                PortraitNoteListScreen(
                    state = state,
                    onAction = onAction
                )
            }

            DeviceConfiguration.TABLET_LANDSCAPE,
            DeviceConfiguration.DESKTOP -> {
                LandscapeOrientationNotesListScreen(
                    state = state,
                    onAction = onAction
                )
            }
        }
    }
}

@ScreenSizesPreview
@Composable
private fun Preview() {
    NotesAppTheme {
        NoteListScreen(
            state = NoteListState(),
            onAction = {}
        )
    }
}