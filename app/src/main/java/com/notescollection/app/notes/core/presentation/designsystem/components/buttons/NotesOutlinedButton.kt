package com.notescollection.app.core.presentation.designsystem.components.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.notescollection.app.notes.core.presentation.designsystem.theme.NotesAppTheme
import com.notescollection.app.notes.core.presentation.designsystem.theme.Primary

@Composable
fun NotesOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    isBorderVisible: Boolean = true
) {
    val buttonModifier = if (isBorderVisible) {
        modifier
            .defaultMinSize(minHeight = 48.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(12.dp),
                color = Primary
            )
    } else {
        modifier.defaultMinSize(minHeight = 48.dp)
    }

    Button(
        onClick = onButtonClick,
        modifier = buttonModifier,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Primary
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = Primary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NNotesOutlinedButtonPreview() {
    NotesAppTheme {
        Column {
            NotesOutlinedButton(
                text = "Label",
                onButtonClick = {},
            )
        }
    }
}