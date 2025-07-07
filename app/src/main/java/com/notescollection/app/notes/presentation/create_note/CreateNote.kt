package com.notescollection.app.notes.presentation.create_note

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.displayCutoutPadding
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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.notescollection.app.notes.core.presentation.designsystem.components.StatusBarStyle
import com.notescollection.app.notes.core.presentation.designsystem.theme.NotesAppTheme
import com.notescollection.app.notes.core.presentation.utils.DeviceConfiguration
import com.notescollection.app.notes.core.presentation.utils.ObserveAsEvents
import com.notescollection.app.notes.core.presentation.utils.ScreenSizesPreview
import com.notescollection.app.notes.presentation.create_note.components.CreateNotePortrait
import com.notescollection.app.notes.presentation.create_note.components.CreateNoteLandscape

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateNoteRoot(
    navigateBack: () -> Unit,
) {
    val viewModel: CreateNoteViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

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
    val context = LocalContext.current
    val activity = context as? Activity

    LaunchedEffect(state.noteMode) {
        if (state.noteMode == NotesMode.READ) {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.surface)
            .displayCutoutPadding(),
    ) {
        StatusBarStyle()
        val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
        val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

        when (deviceConfiguration) {
            DeviceConfiguration.MOBILE_PORTRAIT -> {
                CreateNotePortrait(
                    state = state,
                    onAction = onAction,
                    modifier = Modifier
                )
            }

            DeviceConfiguration.MOBILE_LANDSCAPE -> {
                CreateNoteLandscape(
                    state = state,
                    onAction = onAction,
                    modifier = Modifier
                )
            }

            DeviceConfiguration.TABLET_PORTRAIT,
            DeviceConfiguration.TABLET_LANDSCAPE,
            DeviceConfiguration.DESKTOP -> {
                CreateNotePortrait(
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