package com.notescollection.app.notes.core.presentation.designsystem.components.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.notescollection.app.core.presentation.designsystem.theme.NotesAppTheme
import com.notescollection.app.core.presentation.designsystem.theme.OnSurface12
import com.notescollection.app.core.presentation.designsystem.theme.Primary
import com.notescollection.app.core.presentation.designsystem.theme.onPrimary

@Composable
fun NotesFilledButton(
    text: String,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    enabled: Boolean = true,
    isLeftIconPosition: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
) {
    Button(
        onClick = onButtonClick,
        modifier = modifier.defaultMinSize(minHeight = 48.dp),
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) Primary else OnSurface12,
            disabledContentColor = if (enabled) onPrimary else OnSurface12
        )
    ) {
        if (isLeftIconPosition && icon != null) {
            icon.invoke()
            Spacer(modifier = Modifier.width(8.dp))
        }

        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = if (enabled) onPrimary else OnSurface12
        )

        if (!isLeftIconPosition && icon != null) {
            Spacer(modifier = Modifier.width(8.dp))
            icon.invoke()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotesFilledButtonPreview() {
    NotesAppTheme {
        Column {
            NotesFilledButton(
                text = "Label",
                onButtonClick = {},
                enabled = true,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = ""
                    )
                }
            )

            NotesFilledButton(
                text = "Label",
                onButtonClick = {},
                enabled = false,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = ""
                    )
                }
            )
        }
    }
}