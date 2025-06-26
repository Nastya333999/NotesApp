package com.notescollection.app.notes.presentation.registration.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.notescollection.app.R
import com.notescollection.app.notes.core.presentation.designsystem.components.TitleComponent
import com.notescollection.app.notes.core.presentation.designsystem.theme.LocalAppDimensions
import com.notescollection.app.notes.presentation.registration.RegistrationAction
import com.notescollection.app.notes.presentation.registration.RegistrationState

@Composable
fun TabletRegistrationScreen(
    state: RegistrationState,
    onAction: (RegistrationAction) -> Unit,
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
                vertical = 32.dp
            )
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TitleComponent(
            title = stringResource(R.string.create_account_title),
            subtitle = stringResource(R.string.create_account_subtitle),
            modifier = Modifier.widthIn(max = 540.dp),
            aligment = Alignment.CenterHorizontally
        )

        Spacer(modifier = Modifier.height(40.dp))

        RegistrationContent(
            state = state,
            onAction = onAction,
            modifier = Modifier.widthIn(max = 540.dp),
        )
    }
}
