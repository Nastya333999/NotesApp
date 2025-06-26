package com.notescollection.app.notes.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.notescollection.app.R
import com.notescollection.app.notes.core.presentation.designsystem.components.TitleComponent
import com.notescollection.app.notes.presentation.login.LoginAction
import com.notescollection.app.notes.presentation.login.LoginState

@Composable
fun LandscapeOrientationLoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
) {
    val rootModifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(
            topStart = 15.dp,
            topEnd = 15.dp
        ))
        .consumeWindowInsets(WindowInsets.navigationBars)

    Row(
        modifier = rootModifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
            )
            .padding(
                horizontal = 32.dp,
                vertical = 32.dp,
            )
            .windowInsetsPadding(WindowInsets.displayCutout)
    ) {
        Column(
            modifier = Modifier
                .weight(1.0f)
        ) {
            TitleComponent(
                title = stringResource(R.string.login_title),
                subtitle = stringResource(R.string.login_subtitle),
            )
        }

        Column(
            modifier = Modifier
                .weight(1.0f)
                .verticalScroll(rememberScrollState())
        ) {
            LoginContent(
                state = state,
                onAction = onAction,
            )
        }
    }
}