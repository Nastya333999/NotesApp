package com.notescollection.app.notes.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.notescollection.app.core.presentation.designsystem.components.icons.LogoN
import com.notescollection.app.notes.core.presentation.designsystem.theme.NotesAppTheme
import com.notescollection.app.notes.core.presentation.utils.ScreenSizesPreview

@Composable
fun SplashRoot(
    onNavigateNext: () -> Unit,
    viewModel: SplashViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect {
            onNavigateNext()
        }
    }

    SplashScreen()
}

@Composable
private fun SplashScreen(
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Icon(
            imageVector = LogoN,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@ScreenSizesPreview()
@Composable
private fun Preview() {
    NotesAppTheme {
        SplashScreen()
    }
}
