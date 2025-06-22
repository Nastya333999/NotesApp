package com.notescollection.app.core.presentation.designsystem.components.text_fields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.notescollection.app.core.presentation.designsystem.theme.NotesAppTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.SolidColor

@Composable
fun NotesTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hintText: String? = null,
    label: String? = null,
    supportingText: String? = null,
    hintColor: Color = MaterialTheme.colorScheme.outlineVariant,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge.copy(
        color = MaterialTheme.colorScheme.onSurface,
    ),
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false,
    isError: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    primerColor: Color = MaterialTheme.colorScheme.primary
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val borderColor = when {
        isError -> MaterialTheme.colorScheme.error
        isFocused -> primerColor
        else -> Color.Transparent
    }

    val backgroundColor = when {
        isError -> Color.White
        isFocused -> Color.White
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    val cursorColor =
        if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
    val supportingTextColor =
        if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant

    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = textStyle,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(cursorColor),
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        singleLine = singleLine,
        decorationBox = { innerTextField ->
            Column {
                if (!label.isNullOrBlank()) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 7.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(backgroundColor)
                        .border(
                            width = 1.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (text.isBlank() && hintText != null) {
                        Text(
                            text = hintText,
                            color = hintColor,
                            style = textStyle
                        )
                    }

                    innerTextField()
                }

                if (!supportingText.isNullOrBlank()) {
                    Text(
                        text = supportingText,
                        style = MaterialTheme.typography.bodySmall,
                        color = supportingTextColor,
                        modifier = Modifier.padding(
                            top = 7.dp,
                            start = 12.dp
                        )
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true, name = "Default (Inactive)")
@Composable
private fun NotesTextFieldPreview_Default() {
    NotesAppTheme {
        NotesTextField(
            text = "",
            onValueChange = {},
            label = "Комментарий",
            hintText = "Введите текст",
            supportingText = "Поле для ввода комментария",
            isError = false,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}


@Preview(showBackground = true, name = "Error")
@Composable
private fun NotesTextFieldPreview_Error() {
    NotesAppTheme {
        NotesTextField(
            text = "Ошибка ввода",
            onValueChange = {},
            label = "Поле с ошибкой",
            hintText = "Введите текст",
            supportingText = "Произошла ошибка",
            isError = true,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}