package com.notescollection.app.notes.presentation.create_note.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.notescollection.app.R

@Composable
fun DiscardChangesDialog(
    onDismiss: () -> Unit,
    onDiscardConfirmed: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(stringResource(id = R.string.discard_changes_title))
        },
        text = {
            Text(stringResource(id = R.string.discard_changes_message))
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.keep_editing))
            }
        },
        dismissButton = {
            TextButton(onClick = onDiscardConfirmed) {
                Text(
                    text = stringResource(id = R.string.discard),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}