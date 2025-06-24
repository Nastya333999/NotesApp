package com.notescollection.app.core.presentation.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.notescollection.app.R

val Inter = FontFamily(
    Font(resId = R.font.inter_light)
)

val Grotesk = FontFamily(
    Font(resId = R.font.space_grotesk_medium)
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = Grotesk,
        fontWeight = FontWeight(700),
        fontSize = 36.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.1.sp
    ),

    titleMedium = TextStyle(
        fontFamily = Grotesk,
        fontWeight = FontWeight(700),
        fontSize = 32.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.1.sp
    ),

    titleSmall = TextStyle(
        fontFamily = Grotesk,
        fontWeight = FontWeight(500),
        fontSize = 17.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp
    ),


    bodyLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight(400),
        fontSize = 17.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight(500),
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),

    bodySmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight(400),
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    )
)