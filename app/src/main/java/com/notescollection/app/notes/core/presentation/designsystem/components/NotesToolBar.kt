package com.notescollection.app.notes.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.dropUnlessResumed
import com.notescollection.app.R
import com.notescollection.app.notes.core.presentation.designsystem.theme.Grotesk
import com.notescollection.app.notes.core.presentation.designsystem.theme.NotesAppTheme

@Composable
fun NotesToolBar(
    userFirstLetters: String = "PL",
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 26.dp,
                vertical = 20.dp
            )
    ) {
        Text(
            text = stringResource(R.string.notes_title),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.small
                )
        ) {
            Text(
                text = userFirstLetters,
                style = TextStyle(
                    fontFamily = Grotesk,
                    fontWeight = FontWeight(700),
                    fontSize = 17.sp
                ),
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}


@Composable
fun NotesToolBar(
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 8.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onCancelClick) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Cancel",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

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
                    onSaveClick()
                }
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NotesToolBarPreview() {
    NotesAppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            NotesToolBar()

            HorizontalDivider()

            NotesToolBar(
                onSaveClick = {},
                onCancelClick = {}
            )
        }
    }
}