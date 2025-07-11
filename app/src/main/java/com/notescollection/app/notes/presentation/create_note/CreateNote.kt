package com.notescollection.app.notes.presentation.create_note

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.notescollection.app.notes.core.presentation.designsystem.components.StatusBarStyle
import com.notescollection.app.notes.core.presentation.utils.DeviceConfiguration
import com.notescollection.app.notes.core.presentation.utils.LoadingUiState
import com.notescollection.app.notes.core.presentation.utils.ObserveAsEvents
import com.notescollection.app.notes.presentation.create_note.components.CreateNotePortrait
import com.notescollection.app.notes.presentation.create_note.components.CreateNoteLandscape
import com.notescollection.app.notes.presentation.create_note.components.FadeVisibility
import com.notescollection.app.notes.presentation.create_note.components.NoteStateFAB
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateNoteRoot(
    navigateBack: () -> Unit,
) {
    val viewModel: CreateNoteViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveAsEvents(viewModel.events) { event: CreateNoteEvent ->
        when (event) {
            is CreateNoteEvent.OnSaveClick -> {
                navigateBack()
            }

            is CreateNoteEvent.OnCancelClick -> {
                navigateBack()
            }

            is CreateNoteEvent.ShowToast -> {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    when (state) {

        is LoadingUiState.Loaded<NoteUiState> -> CreateNoteScreen(
            state = (state as LoadingUiState.Loaded<NoteUiState>).data,
            onAction = viewModel::onAction
        )

        is LoadingUiState.Loading -> Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun CreateNoteScreen(
    state: NoteUiState,
    onAction: (CreateNoteAction) -> Unit,
) {
    val context = LocalContext.current
    val activity = context as? Activity

    LaunchedEffect(state.noteMode) {
        activity?.requestedOrientation = when (state.noteMode) {
            NotesMode.READ -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            else -> ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    val isReader = state.noteMode == NotesMode.READ

    var chromeVisible by remember { mutableStateOf(true) }
    var autoJob by remember { mutableStateOf<Job?>(null) }
    val scope = rememberCoroutineScope()

    fun showChromeTemp() {
        chromeVisible = true
        autoJob?.cancel()
        autoJob = scope.launch {
            delay(5_000)
            chromeVisible = false
        }
    }

    fun hideChrome() {
        chromeVisible = false
        autoJob?.cancel()
        autoJob = null
    }

    LaunchedEffect(isReader) {
        if (isReader) {
            showChromeTemp()
        } else {
            chromeVisible = true
            autoJob?.cancel()
            autoJob = null
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(isReader) {
                    if (isReader) {
                        awaitPointerEventScope {
                            while (true) {
                                awaitFirstDown(requireUnconsumed = false)
                                showChromeTemp()
                            }
                        }
                    }
                }

        ) {
            val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
            val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

            val content: @Composable () -> Unit = {
                when (deviceConfiguration) {
                    DeviceConfiguration.MOBILE_LANDSCAPE -> {
                        CreateNoteLandscape(
                            state = state,
                            onAction = onAction,
                            modifier = Modifier,
                            hideChrome = ::hideChrome,
                            chromeVisible = chromeVisible,
                        )
                    }

                    DeviceConfiguration.MOBILE_PORTRAIT,
                    DeviceConfiguration.TABLET_PORTRAIT,
                    DeviceConfiguration.TABLET_LANDSCAPE,
                    DeviceConfiguration.DESKTOP -> {
                        CreateNotePortrait(
                            state = state,
                            onAction = onAction,
                            modifier = Modifier,
                            hideChrome = ::hideChrome,
                            chromeVisible = chromeVisible,
                        )
                    }
                }
            }

            content.invoke()

            if (state.noteMode != NotesMode.CREATE && state.noteMode != NotesMode.EDIT) {
                FadeVisibility(
                    visible = chromeVisible,
                    blockPointer = false,
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    NoteStateFAB(
                        modifier = Modifier.padding(bottom = 16.dp),
                        mode = state.noteMode,
                        onEditIconClicked = { onAction(CreateNoteAction.OnModeChange(NotesMode.EDIT)) },
                        onReadIconClicked = { onAction(CreateNoteAction.OnModeChange(NotesMode.READ)) }
                    )
                }
            }
        }
    }
}

//@ScreenSizesPreview // TODO()
//@Composable
//private fun Preview() {
//    NotesAppTheme {
//        CreateNoteScreen(
//            state = NoteUiState(),
//            onAction = {}
//        )
//    }
//}
