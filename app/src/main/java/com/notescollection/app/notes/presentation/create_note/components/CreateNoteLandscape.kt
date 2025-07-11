package com.notescollection.app.notes.presentation.create_note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.dropUnlessResumed
import com.notescollection.app.R
import com.notescollection.app.notes.core.presentation.utils.asTFV
import com.notescollection.app.notes.core.presentation.designsystem.theme.Grotesk
import com.notescollection.app.notes.presentation.create_note.CreateNoteAction
import com.notescollection.app.notes.presentation.create_note.NoteUiState
import com.notescollection.app.notes.presentation.create_note.NotesMode

@Composable
fun CreateNoteLandscape(
    state: NoteUiState,
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

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        var showDialog by remember { mutableStateOf(false) }

        if (showDialog && state.noteMode in setOf(NotesMode.CREATE, NotesMode.EDIT)) {
            DiscardChangesDialog(
                onDismiss = { showDialog = false },
                onDiscardConfirmed = {
                    showDialog = false
                    onAction(CreateNoteAction.OnCancelClick)
                }
            )
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface)
                .verticalScroll(scrollState)
                .imePadding(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                FadeVisibility(
                    visible = chromeVisible,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = dropUnlessResumed {
                                when (state.noteMode) {
                                    NotesMode.CREATE -> {
                                        showDialog = true
                                    }

                                    NotesMode.READ -> onAction(CreateNoteAction.OnCancelClick)
                                    NotesMode.EDIT -> {
                                        showDialog = true
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Cancel",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        if (state.noteMode != NotesMode.CREATE) {
                            Text(
                                text = stringResource(R.string.tool_bar_back_text).uppercase(),
                                style = TextStyle(
                                    fontFamily = Grotesk,
                                    fontWeight = FontWeight(700),
                                    fontSize = 17.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                NoteTitleField(
                    title = remember(state.note?.title) { (state.note?.title ?: "").asTFV() },
                    onTitleChange = { onAction(CreateNoteAction.OnTitleChange(it)) },
                    focusRequester = focusRequester,
                    readOnly = state.noteMode == NotesMode.READ,
                    modifier = Modifier
                        .widthIn(max = 540.dp)
                        .focusRequester(focusRequester),
                    placeHolder = {
                        Text(
                            text = stringResource(R.string.title_label),
                            style = MaterialTheme.typography.titleLarge,
                        )
                    },
                    maxLines = 1,
                )

                Spacer(modifier = Modifier.weight(1f))

                if (state.noteMode != NotesMode.READ) {
                    Text(
                        text = stringResource(R.string.save_note_text).uppercase(),
                        style = TextStyle(
                            fontFamily = Grotesk,
                            fontWeight = FontWeight(700),
                            fontSize = 17.sp
                        ),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .clickable(
                                onClick = dropUnlessResumed { { onAction(CreateNoteAction.OnSaveClick) } }
                            )
                            .padding(end = 16.dp)
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .widthIn(max = 540.dp)
                    .background(color = MaterialTheme.colorScheme.onSurface)
            )

            if (state.noteMode == NotesMode.READ) {
                NoteMetadata(
                    dateCreated = state.note?.createdAt ?: "",
                    lastModified = state.note?.lastEditedAt ?: "",
                    modifier = Modifier.widthIn(max = 540.dp)
                )

                HorizontalDivider(
                    modifier = Modifier
                        .widthIn(max = 540.dp)
                        .background(color = MaterialTheme.colorScheme.onSurface)
                )
            }

            NoteTitleField(
                title = remember(state.note?.description) {
                    (state.note?.description ?: "").asTFV()
                },
                onTitleChange = { onAction(CreateNoteAction.OnDescriptionChange(it)) },
                readOnly = state.noteMode == NotesMode.READ,
                modifier = modifier
                    .weight(1f)
                    .widthIn(max = 540.dp),
                textStyle = MaterialTheme.typography.bodyLarge,
                placeHolder = {
                    Row(horizontalArrangement = Arrangement.Start) {
                        Text(
                            text = stringResource(R.string.description_label),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            )
        }
    }
}

//@ScreenSizesPreview // TODO()
//@Composable
//private fun Preview() {
//    NotesAppTheme {
//        CreateNoteScreen(
//            state = CreateNoteState(),
//            onAction = {},
//        )
//    }
//}