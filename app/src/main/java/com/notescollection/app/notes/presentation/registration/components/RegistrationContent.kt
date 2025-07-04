package com.notescollection.app.notes.presentation.registration.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.notescollection.app.R
import com.notescollection.app.core.presentation.designsystem.components.buttons.NotesOutlinedButton
import com.notescollection.app.notes.core.presentation.designsystem.components.buttons.NotesFilledButton
import com.notescollection.app.notes.core.presentation.designsystem.components.text_fields.NotesTextField
import com.notescollection.app.notes.presentation.registration.RegistrationAction
import com.notescollection.app.notes.presentation.registration.RegistrationState

@Composable
fun RegistrationContent(
    state: RegistrationState,
    onAction: (RegistrationAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isUserNameFieldFocused: Boolean by remember { mutableStateOf(false) }
    var isPasswordFieldFocused: Boolean by remember { mutableStateOf(false) }

    Column(modifier = modifier) {

        NotesTextField(
            modifier = Modifier.fillMaxWidth(),
            text = state.userName,
            label = stringResource(R.string.username_label),
            onValueChange = { onAction(RegistrationAction.OnUserNameChanged(it)) },
            hintText = stringResource(R.string.email_placeholder),
            supportingText = state.userNameError
                ?: if (isUserNameFieldFocused && state.userName.length < 3)
                    stringResource(R.string.username_hint)
                else "",
            onFocusChanged = { isUserNameFieldFocused = it },
            isError = state.isUserNameError,
        )

        Spacer(modifier = Modifier.height(16.dp))

        NotesTextField(
            modifier = Modifier.fillMaxWidth(),
            text = state.email,
            label = stringResource(R.string.email_label),
            onValueChange = { onAction(RegistrationAction.OnEmailChanged(it)) },
            hintText = stringResource(R.string.email_placeholder),
            isError = state.isEmailError,
            supportingText = state.emailError
        )

        Spacer(modifier = Modifier.height(16.dp))

        NotesTextField(
            modifier = Modifier.fillMaxWidth(),
            text = state.password,
            label = stringResource(R.string.password_label),
            onValueChange = { onAction(RegistrationAction.OnPasswordChanged(it)) },
            hintText = stringResource(R.string.password_label),
            isPassword = true,
            supportingText = state.passwordError
                ?: if (isPasswordFieldFocused && state.password.length < 8)
                    stringResource(R.string.password_hint)
                else "",
            onFocusChanged = { isPasswordFieldFocused = it },
            isError = state.isPasswordError,
            onPasswordToggleClick = {
                onAction(RegistrationAction.OnPasswordVisibilityChange(!state.isPasswordVisible))
            },
            passwordVisible = state.isPasswordVisible,
        )

        Spacer(modifier = Modifier.height(16.dp))

        NotesTextField(
            modifier = Modifier.fillMaxWidth(),
            text = state.repeatPassword,
            label = stringResource(R.string.repeat_password_label),
            onValueChange = { onAction(RegistrationAction.OnRepeatPasswordChanged(it)) },
            hintText = stringResource(R.string.password_label),
            isPassword = true,
            isError = state.isRepeatPasswordError,
            supportingText = state.repeatPasswordError,
            onPasswordToggleClick = {
                onAction(RegistrationAction.OnRepeatPasswordVisibilityChange(!state.isRepeatPasswordVisible))
            },
            passwordVisible = state.isRepeatPasswordVisible,
        )

        Spacer(modifier = Modifier.height(24.dp))

        NotesFilledButton(
            text = stringResource(R.string.create_account_title),
            onButtonClick = { onAction(RegistrationAction.OnCreateAccountClick) },
            enabled = state.isButtonCreateAccountEnabled,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(12.dp))

        NotesOutlinedButton(
            text = stringResource(R.string.already_have_account),
            onButtonClick = { onAction(RegistrationAction.OnLoginClick) },
            modifier = Modifier.fillMaxWidth(),
            isBorderVisible = false,
        )
    }
}