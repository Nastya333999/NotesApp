package com.notescollection.app.notes.presentation.landing.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.notescollection.app.R
import com.notescollection.app.notes.core.presentation.designsystem.components.buttons.NotesFilledButton
import com.notescollection.app.core.presentation.designsystem.components.buttons.NotesOutlinedButton

@Composable
fun ModalBottomSheetContent(
    onGetStartedClick: () -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(R.string.title_landing),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = stringResource(R.string.subtitle_landing),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(40.dp))

        NotesFilledButton(
            text = stringResource(R.string.button_get_started_text),
            onButtonClick = onGetStartedClick,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        NotesOutlinedButton(
            text = stringResource(R.string.button_log_in_text),
            onButtonClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}