package com.notescollection.app.notes.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.notescollection.app.core.presentation.designsystem.components.icons.LogoN
import com.notescollection.app.core.presentation.designsystem.theme.NotesAppTheme

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

@Preview(
    name = "SplashScreen Portrait",
)
@Composable
private fun Preview() {
    NotesAppTheme {
        SplashScreen()
    }
}

@Preview(
    name = "SplashScreen Landscape",
    showBackground = true,
    widthDp = 640,
    heightDp = 360
)
@Composable
private fun PreviewLandscape() {
    NotesAppTheme {
        SplashScreen()
    }
}