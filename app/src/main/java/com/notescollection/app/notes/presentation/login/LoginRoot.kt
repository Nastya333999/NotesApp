package com.notescollection.app.notes.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.notescollection.app.R
import com.notescollection.app.core.presentation.designsystem.components.buttons.NotesFilledButton
import com.notescollection.app.core.presentation.designsystem.components.buttons.NotesOutlinedButton
import com.notescollection.app.core.presentation.designsystem.components.text_fields.NotesTextField
import com.notescollection.app.core.presentation.designsystem.theme.NotesAppTheme
import com.notescollection.app.core.presentation.utils.ScreenSizesPreview

@Composable
fun LoginRoot(
    onLoginClick:() -> Unit,
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is LoginEvent.OnLoginClick -> {
                    onLoginClick()
                }

                is LoginEvent.OnRegisterClick -> {
                    onRegisterClick()
                }
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                )
                .padding(
                    horizontal = 15.dp,
                    vertical = 32.dp
                )
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

            Spacer(modifier = Modifier.height(40.dp))

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