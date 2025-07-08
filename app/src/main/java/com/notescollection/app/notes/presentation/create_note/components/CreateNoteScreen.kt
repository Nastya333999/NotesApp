package com.notescollection.app.notes.presentation.create_note.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import com.notescollection.app.R
import com.notescollection.app.notes.core.presentation.designsystem.components.NotesToolBar
import com.notescollection.app.notes.presentation.create_note.CreateNoteAction
import com.notescollection.app.notes.presentation.create_note.CreateNoteState
import com.notescollection.app.notes.presentation.create_note.NotesMode
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically

@Composable
fun CreateNotePortrait(
    state: CreateNoteState,
    chromeVisible: Boolean,
    onAction: (CreateNoteAction) -> Unit,
    hideChrome: () -> Unit,
    modifier: Modifier
) {
    val focusRequester = remember { FocusRequester() }
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(scrollState.isScrollInProgress) {
        if (scrollState.isScrollInProgress && chromeVisible) hideChrome()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        var showDialog by remember { mutableStateOf(false) }

        if (showDialog) {
            DiscardChangesDialog(
                onDismiss = { showDialog = false },
                onDiscardConfirmed = {
                    showDialog = false
                    onAction(CreateNoteAction.NavigateBack)
                }
            )
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .imePadding()
        ) {
            AnimatedVisibility(
                visible = chromeVisible,
                enter = fadeIn() + slideInVertically { -it / 2 },
                exit = fadeOut() + slideOutVertically { -it / 2 }
            ) {
                NotesToolBar(
                    modifier = Modifier.fillMaxWidth(),
                    onRightTextClick = { onAction(CreateNoteAction.OnSaveClick) },
                    onCancelClick = {
                        if (state.noteForChange != null) {
                            showDialog = true
                        } else {
                            onAction(CreateNoteAction.OnCancelClick)
                        }
                    },
                    leftTextPositioned = if (state.noteMode != NotesMode.CREATE) R.string.tool_bar_back_text else null
                )
            }

            NoteTitleField(
                title = state.title,
                onTitleChange = { onAction(CreateNoteAction.OnTitleChange(it)) },
                focusRequester = focusRequester,
                readOnly = state.noteMode == NotesMode.READ,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .windowInsetsPadding(WindowInsets.displayCutout),
                placeHolder = {
                    Text(
                        text = stringResource(R.string.title_label),
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                maxLines = 1,
            )

            HorizontalDivider(modifier = Modifier.background(color = MaterialTheme.colorScheme.onSurface))

            if (state.noteMode == NotesMode.READ) {
                NoteMetadata(
                    dateCreated = state.noteForChange?.createdAt ?: "",
                    lastModified = state.noteForChange?.lastEditedAt ?: "",
                )
                HorizontalDivider(modifier = Modifier.background(color = MaterialTheme.colorScheme.onSurface))
            }

            NoteTitleField(
                title = state.description,
                onTitleChange = { onAction(CreateNoteAction.OnDescriptionChange(it)) },
                focusRequester = focusRequester,
                readOnly = state.noteMode == NotesMode.READ,
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.displayCutout),
                textStyle = MaterialTheme.typography.bodyLarge,
                placeHolder = {
                    Text(
                        text = stringResource(R.string.description_label),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            )
        }
    }
}