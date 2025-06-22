package com.notescollection.app.notes.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.notescollection.app.notes.presentation.landing.LandingRoot
import com.notescollection.app.notes.presentation.login.LoginRoot
import com.notescollection.app.notes.presentation.splash.SplashRoot

@Composable
fun AppNavHost(
    outerNavController: NavHostController,
) {
    NavHost(
        navController = outerNavController,
        startDestination = Screens.SplashScreen,
        enterTransition = {
            fadeIn(animationSpec = tween(NAVIGATE_ANIMATION_DURATION))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(NAVIGATE_ANIMATION_DURATION))
        }
    ) {
        composable<Screens.SplashScreen> {
            SplashRoot(
                onNavigateNext = {
                    outerNavController.navigate(Screens.LandingScreen)
                }
            )
        }

        composable<Screens.LandingScreen> {
            LandingRoot(
                onStartNavigate = {
                    outerNavController.navigate(Screens.RegisterScreen)
                },
                onLoginNavigate = {
                    outerNavController.navigate(Screens.LoginScreen)
                }
            )
        }

        composable<Screens.LoginScreen> {
            LoginRoot()
        }

        composable<Screens.RegisterScreen> {
        }
    }
}

private const val NAVIGATE_ANIMATION_DURATION = 300