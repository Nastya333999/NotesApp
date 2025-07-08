package com.notescollection.app.notes.presentation.create_note.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun FadeVisibility(
    visible: Boolean,
    blockPointer: Boolean = true,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        label = "fadeAlpha"
    )

    Box(
        modifier = modifier.graphicsLayer { this.alpha = alpha }
            .then(
                if (!visible && blockPointer)
                    Modifier.pointerInput(Unit) {}
                else Modifier
            )
    ) {
        content()
    }
}