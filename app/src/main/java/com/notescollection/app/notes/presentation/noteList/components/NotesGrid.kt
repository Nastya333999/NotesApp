package com.notescollection.app.notes.presentation.noteList.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.notescollection.app.R
import com.notescollection.app.notes.core.presentation.designsystem.theme.NotesAppTheme
import com.notescollection.app.notes.core.presentation.utils.DeviceConfiguration
import com.notescollection.app.notes.presentation.noteList.NoteListAction
import com.notescollection.app.notes.presentation.noteList.NoteListState
import com.notescollection.app.notes.presentation.noteList.models.NoteUiModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun NotesGrid(
    state: NoteListState,
    columns: Int = 2,
    onAction: (NoteListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    var noteToDelete by remember { mutableStateOf<NoteUiModel?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    val notes = state.notesFlow.collectAsLazyPagingItems()

    if (showDialog && noteToDelete != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(stringResource(id = R.string.delete_note_title)) },
            text = { Text(stringResource(id = R.string.delete_note_message)) },
            confirmButton = {
                TextButton(onClick = {
                    onAction(NoteListAction.OnDeleteNote(noteToDelete!!))
                    showDialog = false
                    noteToDelete = null
                }) {
                    Text(stringResource(id = R.string.delete_button))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                    noteToDelete = null
                }) {
                    Text(stringResource(id = R.string.cancel_button))
                }
            }
        )
    }

    val loadState = notes.loadState

    when {
        loadState.refresh is LoadState.Error -> {
            val cause = (loadState.refresh as LoadState.Error).error
            Text(text = "Error: $cause")
        }

        loadState.refresh is LoadState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }

        notes.itemCount == 0 -> {
            NotesEmptyScreen(modifier = Modifier.fillMaxSize())
        }

        else -> {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(columns),
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalItemSpacing = 16.dp
            ) {
                items(notes.itemCount) { index ->
                    notes[index]?.let { note: NoteUiModel ->
                        NoteCard(
                            note = note,
                            onLongClick = {
                                noteToDelete = it
                                showDialog = true
                            },
                            onNoteClick = {
                                onAction(NoteListAction.OnNoteClick(it))
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalFoundationApi::class)
@Composable
fun NoteCard(
    note: NoteUiModel,
    onLongClick: (NoteUiModel) -> Unit,
    onNoteClick: (NoteUiModel) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

    val maxCharacters = when (deviceConfiguration) {
        DeviceConfiguration.MOBILE_PORTRAIT,
        DeviceConfiguration.MOBILE_LANDSCAPE -> 150

        DeviceConfiguration.TABLET_PORTRAIT,
        DeviceConfiguration.TABLET_LANDSCAPE,
        DeviceConfiguration.DESKTOP -> 250
    }

    val truncatedDescription = remember(note.description, maxCharacters) {
        if (note.description.length > maxCharacters)
            note.description.take(maxCharacters) + "…"
        else
            note.description
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .combinedClickable(
                onClick = { onNoteClick(note) },
                onLongClick = { onLongClick(note) }
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = note.date.uppercase(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = truncatedDescription,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    NotesAppTheme {
        NoteCard(
            note = NoteUiModel(
                id = "",
                title = "title",
                description = "description",
                date = "date",
                createdAt = "createdAt",
                lastEditedAt = "lastEditedAt"
            ),
            onLongClick = {},
            onNoteClick = {}
        )
    }
}