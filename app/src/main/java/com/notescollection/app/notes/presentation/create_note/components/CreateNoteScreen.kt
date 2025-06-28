package com.notescollection.app.notes.presentation.create_note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.notescollection.app.R
import com.notescollection.app.notes.core.presentation.designsystem.components.NotesToolBar
import com.notescollection.app.notes.presentation.create_note.CreateNoteAction
import com.notescollection.app.notes.presentation.create_note.CreateNoteState

@Composable
fun CreateNoteContent(
    state: CreateNoteState,
    onAction: (CreateNoteAction) -> Unit,
    modifier: Modifier
) {
    Box(modifier = Modifier.fillMaxSize()) {
        var showDialog by remember { mutableStateOf(false) }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(stringResource(id = R.string.discard_changes_title)) },
                text = { Text(stringResource(id = R.string.discard_changes_message)) },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                    }) {
                        Text(stringResource(id = R.string.keep_editing))
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDialog = false
                        onAction(CreateNoteAction.NavigateBack)
                    }) {
                        Text(
                            stringResource(id = R.string.discard),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                )
                .verticalScroll(rememberScrollState())
        ) {
            NotesToolBar(
                modifier = Modifier.fillMaxWidth(),
                onSaveClick = { onAction(CreateNoteAction.OnSaveClick) },
                onCancelClick = {
                    if (state.noteForChange != null) {
                        showDialog = true
                    } else {
                        onAction(CreateNoteAction.OnCancelClick)
                    }
                }
            ) // TODO() lkz landscape add pading

            TextField(
                value = state.title,
                onValueChange = { onAction(CreateNoteAction.OnTitleChange(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.displayCutout),
                placeholder = {
                    Text(
                        text = stringResource(R.string.title_label),
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                maxLines = 1,
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                ),
                textStyle = MaterialTheme.typography.titleLarge,
            )

            HorizontalDivider(modifier = Modifier.background(color = MaterialTheme.colorScheme.onSurface))

            TextField(
                value = state.description,
                onValueChange = { onAction(CreateNoteAction.OnDescriptionChange(it)) },
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.displayCutout)
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = stringResource(R.string.description_label),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                },
                maxLines = 1,
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                ),
                textStyle = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}