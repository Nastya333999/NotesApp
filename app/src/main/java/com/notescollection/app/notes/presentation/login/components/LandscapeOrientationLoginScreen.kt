package com.notescollection.app.notes.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.notescollection.app.R
import com.notescollection.app.core.presentation.designsystem.components.buttons.NotesFilledButton
import com.notescollection.app.core.presentation.designsystem.components.buttons.NotesOutlinedButton
import com.notescollection.app.core.presentation.designsystem.components.text_fields.NotesTextField
import com.notescollection.app.core.presentation.designsystem.theme.LocalAppDimensions
import com.notescollection.app.notes.presentation.login.LoginAction
import com.notescollection.app.notes.presentation.login.LoginState


@Composable
fun LandscapeOrientationLoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
) {

    val horizontalPadding: Dp = LocalAppDimensions.current.loginContentHorizontalPaddingStart

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
            )
            .padding(
                horizontal = horizontalPadding,
                vertical = 32.dp
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(start = LocalAppDimensions.current.textHorizontalPadding)
        ) {
            Text(
                text = stringResource(R.string.login_title),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = stringResource(R.string.login_subtitle),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(end = LocalAppDimensions.current.loginContentHorizontalPaddingEnd)
        ) {
            NotesTextField(
                modifier = Modifier.fillMaxWidth(),
                text = state.email,
                label = stringResource(R.string.email_label),
                onValueChange = { onAction(LoginAction.OnEmailChange(it)) },
                hintText = stringResource(R.string.email_placeholder),
            )

            Spacer(modifier = Modifier.height(16.dp))

            NotesTextField(
                modifier = Modifier.fillMaxWidth(),
                text = state.password,
                label = stringResource(R.string.password_label),
                onValueChange = { onAction(LoginAction.OnPasswordChange(it)) },
                hintText = stringResource(R.string.password_label),
                isPassword = true,
            )

            Spacer(modifier = Modifier.height(24.dp))

            NotesFilledButton(
                text = stringResource(R.string.login_button),
                onButtonClick = { onAction(LoginAction.OnLoginClick) },
                enabled = true,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(12.dp))

            NotesOutlinedButton(
                text = stringResource(R.string.no_account),
                onButtonClick = { onAction(LoginAction.OnRegisterClick) },
                modifier = Modifier.fillMaxWidth(),
                isBorderVisible = false,
            )
        }

    }
}