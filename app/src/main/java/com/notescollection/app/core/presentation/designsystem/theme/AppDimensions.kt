package com.notescollection.app.core.presentation.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppDimensions(
    val screenHorizontalPadding: Dp
)

val CompactDimensions = AppDimensions(screenHorizontalPadding = 16.dp)
val TabletDimensions = AppDimensions(screenHorizontalPadding = 60.dp)

val LocalAppDimensions = staticCompositionLocalOf { CompactDimensions }