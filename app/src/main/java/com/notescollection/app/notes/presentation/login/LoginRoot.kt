package com.notescollection.app.notes.presentation.login

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.notescollection.app.R
import com.notescollection.app.core.presentation.designsystem.components.buttons.NotesFilledButton
import com.notescollection.app.core.presentation.designsystem.components.buttons.NotesOutlinedButton
import com.notescollection.app.core.presentation.designsystem.components.text_fields.NotesTextField
import com.notescollection.app.core.presentation.designsystem.theme.LocalAppDimensions
import com.notescollection.app.core.presentation.designsystem.theme.NotesAppTheme
import com.notescollection.app.core.presentation.utils.ObserveAsEvents
import com.notescollection.app.core.presentation.utils.ScreenSizesPreview
import com.notescollection.app.notes.presentation.login.components.LandscapeOrientationLoginScreen
import com.notescollection.app.notes.presentation.login.components.PortraitLoginScreen

@Composable
fun LoginRoot(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is LoginEvent.OnLoginClick -> {
                onLoginClick()
            }

            is LoginEvent.OnRegisterClick -> {
                onRegisterClick()
            }
        }
    }

    LoginScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
            .statusBarsPadding()
            .padding(top = 8.dp)
    ) {

        val configuration = LocalConfiguration.current
        val orientation = configuration.orientation

        when (orientation) {
            Configuration.ORIENTATION_PORTRAIT -> PortraitLoginScreen(
                state = state,
                onAction = onAction
            )

            else -> LandscapeOrientationLoginScreen(
                state = state,
                onAction = onAction
            )
        }
    }
}

@ScreenSizesPreview
@Composable
private fun PreviewLandscape() {
    NotesAppTheme {
        LoginScreen(
            state = LoginState(),
            onAction = {},
        )
    }
}