package com.notescollection.app.notes.presentation.create_note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.notescollection.app.notes.core.presentation.designsystem.components.icons.IconBookOpen
import com.notescollection.app.notes.core.presentation.designsystem.components.icons.IconEdit
import com.notescollection.app.notes.core.presentation.designsystem.theme.Surface
import com.notescollection.app.notes.presentation.create_note.NotesMode

@Composable
fun NoteStateFAB(
    mode: NotesMode,
    onEditIconClicked: () -> Unit,
    onReadIconClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val color = MaterialTheme.colorScheme.primary
    val fabModifier = Modifier.padding(4.dp)

    Row(
        modifier = modifier
            .background(
                color = Surface,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        FloatingActionButton(
            onClick = onEditIconClicked,
            containerColor = if (mode == NotesMode.EDIT) color.copy(alpha = 0.1f) else Surface,
            elevation = FloatingActionButtonDefaults.elevation(0.dp),
            modifier =fabModifier
        ) {
            Icon(
                imageVector = IconEdit,
                contentDescription = "IconEdit",
                tint = if (mode == NotesMode.EDIT) color else MaterialTheme.colorScheme.onSurface,
            )
        }
        FloatingActionButton(
            onClick = onReadIconClicked,
            containerColor = if (mode == NotesMode.READ) color.copy(alpha = 0.1f) else Surface,
            elevation = FloatingActionButtonDefaults.elevation(0.dp),
            modifier = fabModifier
        ) {
            Icon(
                imageVector = IconBookOpen,
                contentDescription = "IconOpenBook",
                tint = if (mode == NotesMode.READ) color else MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Preview
@Composable
fun NoteStateFABPreview() {
    NoteStateFAB(
        mode = NotesMode.READ,
        onEditIconClicked = {},
        onReadIconClicked = {}
    )
}