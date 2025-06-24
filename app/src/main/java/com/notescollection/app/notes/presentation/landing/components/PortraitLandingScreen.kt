package com.notescollection.app.notes.presentation.landing.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.notescollection.app.R
import com.notescollection.app.core.presentation.designsystem.theme.LandingScreenBackground
import com.notescollection.app.notes.core.presentation.designsystem.theme.LocalAppDimensions
import com.notescollection.app.notes.presentation.landing.LandingAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortraitLandingScreen(
    onAction: (LandingAction) -> Unit,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val imageHeight = screenHeight * 0.8f
    val horizontalPadding = LocalAppDimensions.current.screenHorizontalPadding

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LandingScreenBackground)
    ) {
        Image(
            painter = painterResource(id = R.drawable.langing_image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                )
        ) {
            ModalBottomSheetContent(
                onGetStartedClick = { onAction(LandingAction.GetStartedClick) },
                onLoginClick = { onAction(LandingAction.LoginClick) },
                paddingValues = PaddingValues(horizontal = horizontalPadding)
            )
        }
    }
}