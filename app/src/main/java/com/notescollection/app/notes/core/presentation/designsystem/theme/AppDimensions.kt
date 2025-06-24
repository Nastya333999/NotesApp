package com.notescollection.app.notes.core.presentation.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

val CompactDimensions = Dimensions(
    screenHorizontalPadding = 16.dp,
    loginContentHorizontalPaddingStart = 16.dp,
    cardPadding = 12.dp,
    settingsContentSpacing = 8.dp,
    textHorizontalPadding = 16.dp,
    loginContentHorizontalPaddingEnd = 40.dp,
)

val TabletDimensions = Dimensions(
    screenHorizontalPadding = 60.dp,
    loginContentHorizontalPaddingStart = 120.dp,
    cardPadding = 24.dp,
    settingsContentSpacing = 16.dp,
    textHorizontalPadding = 60.dp,
)

val LocalAppDimensions = staticCompositionLocalOf { CompactDimensions }