package com.notescollection.app.notes.presentation.create_note

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.notescollection.app.notes.core.presentation.designsystem.theme.NotesAppTheme
import com.notescollection.app.notes.core.presentation.utils.DeviceConfiguration
import com.notescollection.app.notes.core.presentation.utils.ObserveAsEvents
import com.notescollection.app.notes.core.presentation.utils.ScreenSizesPreview
import com.notescollection.app.notes.presentation.create_note.components.CreateNoteContent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateNoteRoot(
    navigateBack: () -> Unit,
    noteId: String? = null
) {
    val viewModel: CreateNoteViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(noteId) {
        if (noteId == null) {
            viewModel.init(null)
        } else {
            viewModel.loadNote(noteId)
        }
    }

    ObserveAsEvents(viewModel.events) { event: CreateNoteEvent ->
        when (event) {
            is CreateNoteEvent.OnSaveClick -> {
                navigateBack()
            }

            is CreateNoteEvent.OnCancelClick -> {
                navigateBack()
            }
        }
    }

    CreateNoteScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun CreateNoteScreen(
    state: CreateNoteState,
    onAction: (CreateNoteAction) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.primary),
    ) {
        val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
        val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
        when (deviceConfiguration) {
            DeviceConfiguration.MOBILE_PORTRAIT -> {
                CreateNoteContent(
                    state = state,
                    onAction = onAction,
                    modifier = Modifier
                )
            }

            DeviceConfiguration.MOBILE_LANDSCAPE -> {
                CreateNoteContent(
                    state = state,
                    onAction = onAction,
                    modifier = Modifier
                )
            }

            DeviceConfiguration.TABLET_PORTRAIT,
            DeviceConfiguration.TABLET_LANDSCAPE,
            DeviceConfiguration.DESKTOP -> {
                CreateNoteContent(
                    state = state,
                    onAction = onAction,
                    modifier = Modifier
                )
            }
        }
    }
}

@ScreenSizesPreview
@Composable
private fun Preview() {
    NotesAppTheme {
        CreateNoteScreen(
            state = CreateNoteState(),
            onAction = {}
        )
    }
}