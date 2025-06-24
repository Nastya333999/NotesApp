package com.notescollection.app.notes.presentation.registration.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.notescollection.app.notes.core.presentation.designsystem.theme.LocalAppDimensions
import com.notescollection.app.core.presentation.designsystem.theme.NotesAppTheme
import com.notescollection.app.notes.core.presentation.utils.ScreenSizesPreview
import com.notescollection.app.notes.core.presentation.designsystem.components.buttons.NotesFilledButton
import com.notescollection.app.notes.core.presentation.designsystem.components.text_fields.NotesTextField
import com.notescollection.app.notes.presentation.registration.RegistrationAction
import com.notescollection.app.notes.presentation.registration.RegistrationState

@Composable
fun LandscapeOrientationRegistrationScreen(
    state: RegistrationState,
    onAction: (RegistrationAction) -> Unit,
) {
    var isUserNameFieldFocused by remember { mutableStateOf(false) }
    var isPasswordFieldFocused by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
            )
            .padding(
                start = 60.dp,
                top = 32.dp
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(start = LocalAppDimensions.current.textHorizontalPadding)
        ) {
            Text(
                text = stringResource(R.string.create_account_title),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = stringResource(R.string.create_account_subtitle),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(
                    start = LocalAppDimensions.current.textHorizontalPadding,
                    end = LocalAppDimensions.current.loginContentHorizontalPaddingEnd,
                )
                .verticalScroll(rememberScrollState())
        ) {
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
                supportingText = state.repeatPasswordError
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
}

@ScreenSizesPreview
@Composable
private fun Preview() {
    NotesAppTheme {
        LandscapeOrientationRegistrationScreen(
            state = RegistrationState(),
            onAction = {}
        )
    }
}