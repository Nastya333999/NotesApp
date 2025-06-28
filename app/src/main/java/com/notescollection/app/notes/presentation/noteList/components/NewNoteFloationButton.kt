package com.notescollection.app.notes.presentation.noteList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NewNoteFloatingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        onClick = { onClick() },
        shape = RoundedCornerShape(20.dp),
        containerColor = Color.Transparent,
        contentColor = Color.White,
        modifier = modifier.size(64.dp)
    ) {
        Box(
            modifier = Modifier.size(64.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF58A1F8),
                            Color(0xFF5A4CF7)
                        )
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
            ,
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Preview
@Composable
fun NewNoteFloatingButtonPreview() {
    NewNoteFloatingButton(
        onClick = {}
    )
}