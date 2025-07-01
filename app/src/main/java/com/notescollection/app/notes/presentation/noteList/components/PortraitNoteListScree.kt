package com.notescollection.app.notes.presentation.noteList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.notescollection.app.notes.core.presentation.designsystem.components.NotesToolBar
import com.notescollection.app.notes.presentation.noteList.NoteListAction
import com.notescollection.app.notes.presentation.noteList.NoteListState

@Composable
fun PortraitNoteListScreen(
    state: NoteListState,
    onAction: (NoteListAction) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            NotesToolBar(
                modifier = Modifier.fillMaxWidth(),
                userFirstLetters = state.userName,
                onSettingsIconClicked = { onAction(NoteListAction.OnSettingsClick) },
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                NotesGrid(
                    state = state,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    onAction = onAction,
                )
            }
        }

        NewNoteFloatingButton(
            onClick = { onAction(NoteListAction.OnNewNoteCreate) },
            modifier = Modifier
                .padding(24.dp)
                .align(alignment = Alignment.BottomEnd)
        )
    }
}

