package com.notescollection.app.notes.presentation.landing

import android.content.res.Configuration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.viewmodel.compose.viewModel
import com.notescollection.app.core.presentation.designsystem.theme.NotesAppTheme
import com.notescollection.app.core.presentation.utils.ObserveAsEvents
import com.notescollection.app.notes.core.presentation.utils.ScreenSizesPreview
import com.notescollection.app.notes.presentation.landing.components.LandscapeOrientationLandingScreen
import com.notescollection.app.notes.presentation.landing.components.PortraitLandingScreen

@Composable
fun LandingRoot(
    onStartNavigate: () -> Unit,
    onLoginNavigate: () -> Unit,
    viewModel: LandingViewModel = viewModel()
) {
    ObserveAsEvents(viewModel.events) { event: LandingEvent ->
        when (event) {
            is LandingEvent.GetStartedClick -> {
                onStartNavigate()
            }

            is LandingEvent.LoginClick -> {
                onLoginNavigate()
            }
        }
    }

    LandingScreen(
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen(
    onAction: (LandingAction) -> Unit,
) {
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    when (orientation) {
        Configuration.ORIENTATION_PORTRAIT -> PortraitLandingScreen(onAction = onAction)
        else -> LandscapeOrientationLandingScreen(onAction = onAction)
    }
}

@ScreenSizesPreview()
@Composable
private fun PreviewLandscape() {
    NotesAppTheme {
        LandingScreen(
            onAction = {},
        )
    }
}