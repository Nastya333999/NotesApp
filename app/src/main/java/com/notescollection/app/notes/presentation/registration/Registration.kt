package com.notescollection.app.notes.presentation.registration

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.notescollection.app.notes.core.presentation.designsystem.components.CustomToast
import com.notescollection.app.notes.core.presentation.designsystem.theme.NotesAppTheme
import com.notescollection.app.notes.core.presentation.utils.DeviceConfiguration
import com.notescollection.app.notes.core.presentation.utils.ObserveAsEvents
import com.notescollection.app.notes.core.presentation.utils.ScreenSizesPreview
import com.notescollection.app.notes.presentation.login.components.LandscapeOrientationLoginScreen
import com.notescollection.app.notes.presentation.login.components.PortraitLoginScreen
import com.notescollection.app.notes.presentation.login.components.TabletLoginScreen
import com.notescollection.app.notes.presentation.registration.components.LandscapeOrientationRegistrationScreen
import com.notescollection.app.notes.presentation.registration.components.PortraitRegistrationScreen
import com.notescollection.app.notes.presentation.registration.components.TabletRegistrationScreen
import kotlinx.coroutines.delay

@Composable
fun RegistrationRoot(
    onLoginClick: () -> Unit,
) {
    val viewModel: RegistrationViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is RegistrationEvent.OnLoginClick -> {
                onLoginClick()
            }

            is RegistrationEvent.OnCreateAccount -> {
                onLoginClick()
            }
            is RegistrationEvent.ShowToast -> {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    RegistrationScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun RegistrationScreen(
    state: RegistrationState,
    onAction: (RegistrationAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
            .statusBarsPadding()
            .padding(top = 8.dp)
    ) {

        state.toastText?.let { text ->
            LaunchedEffect(text) {
                delay(2000)
                onAction(RegistrationAction.OnToastShown)
            }

            CustomToast(
                text = text,
                isError = state.isToastError,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 72.dp)
            )
        }

        val configuration = LocalConfiguration.current
        val orientation = configuration.orientation

        when (orientation) {
            Configuration.ORIENTATION_PORTRAIT -> PortraitRegistrationScreen(
                state = state,
                onAction = onAction
            )

            else -> LandscapeOrientationRegistrationScreen(
                state = state,
                onAction = onAction
            )
        }

        val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
        val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
        when(deviceConfiguration) {

            DeviceConfiguration.MOBILE_PORTRAIT -> {
                PortraitRegistrationScreen(
                    state = state,
                    onAction = onAction
                )
            }
            DeviceConfiguration.MOBILE_LANDSCAPE -> {
                LandscapeOrientationRegistrationScreen(
                    state = state,
                    onAction = onAction
                )
            }

            DeviceConfiguration.TABLET_PORTRAIT,
            DeviceConfiguration.TABLET_LANDSCAPE,
            DeviceConfiguration.DESKTOP -> {
                TabletRegistrationScreen(
                    state = state,
                    onAction = onAction,
                )
            }
        }
    }
}

@ScreenSizesPreview
@Composable
private fun Preview() {
    NotesAppTheme {
        RegistrationScreen(
            state = RegistrationState(),
            onAction = {}
        )
    }
}