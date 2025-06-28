package com.notescollection.app.notes.presentation.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.notescollection.app.notes.core.presentation.designsystem.theme.NotesAppTheme
import com.notescollection.app.notes.core.presentation.utils.DeviceConfiguration
import com.notescollection.app.notes.core.presentation.utils.ObserveAsEvents
import com.notescollection.app.notes.core.presentation.utils.ScreenSizesPreview
import com.notescollection.app.notes.presentation.login.components.LandscapeOrientationLoginScreen
import com.notescollection.app.notes.presentation.login.components.PortraitLoginScreen
import com.notescollection.app.notes.presentation.login.components.TabletLoginScreen

@Composable
fun LoginRoot(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    val viewModel: LoginViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is LoginEvent.OnLoginClick -> {
                Log.d("LoginViewModeleeee", "  is LoginEvent.OnLoginClick -> {")

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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
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

        val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
        val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
        when (deviceConfiguration) {

            DeviceConfiguration.MOBILE_PORTRAIT -> {
                PortraitLoginScreen(
                    state = state,
                    onAction = onAction
                )
            }

            DeviceConfiguration.MOBILE_LANDSCAPE -> {
                LandscapeOrientationLoginScreen(
                    state = state,
                    onAction = onAction,
                )
            }

            DeviceConfiguration.TABLET_PORTRAIT,
            DeviceConfiguration.TABLET_LANDSCAPE,
            DeviceConfiguration.DESKTOP -> {
                TabletLoginScreen(
                    state = state,
                    onAction = onAction,
                )
            }
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