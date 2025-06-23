package com.notescollection.app.notes.presentation.landing.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.notescollection.app.R
import com.notescollection.app.core.presentation.designsystem.theme.LandingScreenBackground
import com.notescollection.app.core.presentation.designsystem.theme.LocalAppDimensions
import com.notescollection.app.notes.presentation.landing.LandingAction

@Composable
fun LandscapeOrientationLandingScreen(
    onAction: (LandingAction) -> Unit,
) {
    val horizontalPadding = LocalAppDimensions.current.screenHorizontalPadding
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LandingScreenBackground)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier.weight(1.0f)) {
                Image(
                    painter = painterResource(id = R.drawable.langing_image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Box(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .weight(1.0f)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(20.dp, 0.dp, 0.dp, 20.dp)
                    )
            ) {
                ModalBottomSheetContent(
                    onGetStartedClick = { onAction(LandingAction.GetStartedClick) },
                    onLoginClick = { onAction(LandingAction.GetStartedClick) },
                    paddingValues = PaddingValues(
                        vertical = horizontalPadding,
                        horizontal = 32.dp
                    )
                )
            }
        }
    }
}
