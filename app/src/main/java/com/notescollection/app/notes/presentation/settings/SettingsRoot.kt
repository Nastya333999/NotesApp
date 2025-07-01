package com.notescollection.app.notes.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.dropUnlessResumed
import com.notescollection.app.R
import com.notescollection.app.notes.core.presentation.designsystem.components.NotesToolBar
import com.notescollection.app.notes.core.presentation.designsystem.theme.NotesAppTheme
import com.notescollection.app.notes.core.presentation.utils.DeviceConfiguration
import com.notescollection.app.notes.core.presentation.utils.ObserveAsEvents
import com.notescollection.app.notes.core.presentation.utils.ScreenSizesPreview

@Composable
fun SettingsRoot(
    navigateBack: () -> Unit,
    logOut: () -> Unit
) {
    val viewModel: SettingsViewModel = hiltViewModel()

    ObserveAsEvents(viewModel.events) { event: SettingsEvent ->
        when (event) {
            is SettingsEvent.OnLogoutEvent -> {
                navigateBack()
            }

            is SettingsEvent.OnBackEvent -> {
                logOut()
            }
        }
    }

    SettingsScreen(
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SettingsScreen(
    onAction: (SettingsAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
            .displayCutoutPadding()
            .statusBarsPadding()
    ) {

        val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
        val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
        when (deviceConfiguration) {
            DeviceConfiguration.MOBILE_PORTRAIT -> {
                SettingsContent(
                    onAction = onAction
                )
            }

            DeviceConfiguration.MOBILE_LANDSCAPE -> {
                SettingsContent(
                    onAction = onAction
                )
            }

            DeviceConfiguration.TABLET_PORTRAIT,
            DeviceConfiguration.TABLET_LANDSCAPE,
            DeviceConfiguration.DESKTOP -> {
                SettingsContent(
                    onAction = onAction,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }
    }
}

@Composable
fun SettingsContent(
    onAction: (SettingsAction) -> Unit,
    modifier: Modifier = Modifier
) {
    NotesToolBar(
        onCancelClick = { onAction(SettingsAction.OnBackClick) },
        leftTextPositioned = R.string.settings_title,
        rightTextPositioned = null,
        modifier = modifier
    )

    SettingsMenuItem(
        onItemClick = { onAction(SettingsAction.OnLogoutClick) },
        title = stringResource(id = R.string.log_out_items),
        color = MaterialTheme.colorScheme.error,
        icon = Icons.AutoMirrored.Filled.Logout,
        modifier = modifier
    )
}

@Composable
fun SettingsMenuItem(
    onItemClick: () -> Unit,
    title: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(onClick = dropUnlessResumed { onItemClick() }),
        verticalAlignment = Alignment.CenterVertically
    ) {
            Icon(
                imageVector = icon,
                tint = color,
                contentDescription = title,
                modifier = Modifier.padding(start = 4.dp)
            )
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = color,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@ScreenSizesPreview
@Composable
private fun Preview() {
    NotesAppTheme {
        SettingsScreen(
            onAction = {}
        )
    }
}