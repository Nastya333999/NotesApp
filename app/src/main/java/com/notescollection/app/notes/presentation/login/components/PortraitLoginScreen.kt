package com.notescollection.app.notes.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.notescollection.app.R
import com.notescollection.app.notes.core.presentation.designsystem.components.TitleComponent
import com.notescollection.app.notes.core.presentation.designsystem.theme.LocalAppDimensions
import com.notescollection.app.notes.presentation.login.LoginAction
import com.notescollection.app.notes.presentation.login.LoginState

@Composable
fun PortraitLoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
) {
    val horizontalPadding: Dp = LocalAppDimensions.current.loginContentHorizontalPaddingStart

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
            )
            .padding(
                horizontal = horizontalPadding,
                vertical = 32.dp,
            )
    ) {

        TitleComponent(
            title = stringResource(R.string.login_title),
            subtitle = stringResource(R.string.login_subtitle),
        )

        Spacer(modifier = Modifier.height(40.dp))

        LoginContent(
            state = state,
            onAction = onAction,
        )
    }
}