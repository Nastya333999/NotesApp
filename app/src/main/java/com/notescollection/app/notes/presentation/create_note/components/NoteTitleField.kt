package com.notescollection.app.notes.presentation.create_note.components

import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun NoteTitleField(
    title: TextFieldValue,
    onTitleChange: (TextFieldValue) -> Unit,
    focusRequester: FocusRequester,
    readOnly: Boolean,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge,
    placeHolder: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = title,
        onValueChange = { onTitleChange(it) },
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .windowInsetsPadding(WindowInsets.displayCutout),
        placeholder = { placeHolder.invoke() },
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
        textStyle = textStyle,
        readOnly = readOnly
    )
}
