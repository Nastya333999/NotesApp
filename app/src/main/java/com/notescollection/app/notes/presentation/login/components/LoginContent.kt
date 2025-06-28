package com.notescollection.app.notes.presentation.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.notescollection.app.R
import com.notescollection.app.core.presentation.designsystem.components.buttons.NotesOutlinedButton
import com.notescollection.app.notes.core.presentation.designsystem.components.buttons.NotesFilledButton
import com.notescollection.app.notes.core.presentation.designsystem.components.text_fields.NotesTextField
import com.notescollection.app.notes.presentation.login.LoginAction
import com.notescollection.app.notes.presentation.login.LoginState

@Composable
fun LoginContent(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
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
            onPasswordToggleClick = { onAction(LoginAction.OnPasswordVisibilityChange(!state.isPasswordVisible)) },
            passwordVisible = state.isPasswordVisible,
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

        Spacer(modifier = Modifier.height(16.dp))
    }
}
