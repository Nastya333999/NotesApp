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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.dropUnlessResumed
import com.notescollection.app.R
import com.notescollection.app.notes.core.presentation.designsystem.components.icons.Settings
import com.notescollection.app.notes.core.presentation.designsystem.theme.Grotesk
import com.notescollection.app.notes.core.presentation.designsystem.theme.NotesAppTheme

@Composable
fun NotesToolBar(
    userFirstLetters: String = "PL",
    modifier: Modifier = Modifier,
    onSettingsIconClicked: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 26.dp,
                vertical = 20.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.notes_title),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = dropUnlessResumed { onSettingsIconClicked() }) {
            Icon(
                imageVector = Settings,
                contentDescription = "Clear",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
        }
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
    onRightTextClick: () -> Unit = {},
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier,
    leftIcon: ImageVector = Icons.Default.Clear,
    rightTextPositioned: Int? = R.string.save_note_text,
    rightTextColor: Color = MaterialTheme.colorScheme.primary,
    leftTextPositioned: Int? = null,
    leftTextColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                imageVector = leftIcon,
                contentDescription = "Cancel",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        leftTextPositioned?.let {
            Text(
                text = stringResource(leftTextPositioned).uppercase(),
                style = TextStyle(
                    fontFamily = Grotesk,
                    fontWeight = FontWeight(700),
                    fontSize = 17.sp
                ),
                color = leftTextColor,
                modifier = Modifier
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        rightTextPositioned?.let {
            Text(
                text = stringResource(rightTextPositioned).uppercase(),
                style = TextStyle(
                    fontFamily = Grotesk,
                    fontWeight = FontWeight(700),
                    fontSize = 17.sp
                ),
                color = rightTextColor,
                modifier = Modifier.clickable(
                    onClick = dropUnlessResumed {
                        onRightTextClick()
                    }
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotesToolBarPreview() {
    NotesAppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            NotesToolBar(
                onSettingsIconClicked = {},
            )

            HorizontalDivider()

            NotesToolBar(
                onRightTextClick = {},
                onCancelClick = {},
                leftTextPositioned = R.string.settings_title
            )
        }
    }
}