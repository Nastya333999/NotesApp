package com.notescollection.app.notes.presentation.create_note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.dropUnlessResumed
import com.notescollection.app.R
import com.notescollection.app.notes.core.presentation.designsystem.theme.Grotesk
import com.notescollection.app.notes.core.presentation.designsystem.theme.NotesAppTheme
import com.notescollection.app.notes.core.presentation.utils.ScreenSizesPreview
import com.notescollection.app.notes.presentation.create_note.CreateNoteAction
import com.notescollection.app.notes.presentation.create_note.CreateNoteScreen
import com.notescollection.app.notes.presentation.create_note.CreateNoteState

@Composable
fun CreateNoteLandscape(
    state: CreateNoteState,
    onAction: (CreateNoteAction) -> Unit,
    modifier: Modifier
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .displayCutoutPadding()
    ) {
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
                .background(color = MaterialTheme.colorScheme.surface)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = dropUnlessResumed {
                    if (state.noteForChange != null) {
                        showDialog = true
                    } else {
                        onAction(CreateNoteAction.OnCancelClick)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Cancel",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.weight(1f))

                TextField(
                    value = state.title,
                    onValueChange = { onAction(CreateNoteAction.OnTitleChange(it)) },
                    modifier = Modifier
                        .defaultMinSize(minWidth = 540.dp)
                        .padding(horizontal = 16.dp)
                        .focusRequester(focusRequester),
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

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = stringResource(R.string.save_note_text).uppercase(),
                    style = TextStyle(
                        fontFamily = Grotesk,
                        fontWeight = FontWeight(700),
                        fontSize = 17.sp
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable(
                        onClick = dropUnlessResumed {
                            { onAction(CreateNoteAction.OnSaveClick) }
                        }
                    )
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .widthIn(max = 540.dp)
                    .background(color = MaterialTheme.colorScheme.onSurface)
            )
            Box(
                modifier = Modifier
                    .widthIn(max = 540.dp)
                    .weight(1f)
            ) {
                TextField(
                    value = state.description,
                    onValueChange = { onAction(CreateNoteAction.OnDescriptionChange(it)) },
                    modifier = Modifier
                        .defaultMinSize(minWidth = 540.dp)
                        .windowInsetsPadding(WindowInsets.displayCutout),
                    placeholder = {
                        Row(horizontalArrangement = Arrangement.Start) {
                            Text(
                                text = stringResource(R.string.description_label),
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Start
                            )
                        }
                    },
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