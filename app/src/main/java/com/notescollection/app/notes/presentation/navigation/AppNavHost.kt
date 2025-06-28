package com.notescollection.app.notes.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.notescollection.app.notes.presentation.create_note.CreateNoteRoot
import com.notescollection.app.notes.presentation.landing.LandingRoot
import com.notescollection.app.notes.presentation.login.LoginRoot
import com.notescollection.app.notes.presentation.noteList.NoteListRoot
import com.notescollection.app.notes.presentation.registration.RegistrationRoot
import com.notescollection.app.notes.presentation.splash.SplashRoot

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    outerNavController: NavHostController,
    startDestination: Screens,
) {
    NavHost(
        navController = outerNavController,
        startDestination = startDestination,
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
                    outerNavController.navigate(Screens.LandingScreen) {
                        popUpTo(Screens.SplashScreen) {
                            inclusive = true
                        }
                    }
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
            LoginRoot(
                onLoginClick = {
                    outerNavController.navigate(Screens.NoteListScreen)
                },
                onRegisterClick = {
                    outerNavController.navigate(Screens.RegisterScreen)
                },
            )
        }

        composable<Screens.RegisterScreen> {
            RegistrationRoot(
                onLoginClick = {
                    outerNavController.navigate(Screens.LoginScreen)
                },
            )
        }

        composable<Screens.NoteListScreen> {
            NoteListRoot(
                navigateToCreateNote = {
                    outerNavController.navigate(Screens.CreateNoteScreen.route())
                },
                onNoteDetailsClick = { noteId ->
                    outerNavController.navigate(Screens.CreateNoteScreen.route(noteId))
                }
            )
        }

        composable(
            route = "${Screens.CreateNoteScreen.routeBase}/{${Screens.CreateNoteScreen.noteIdArg}}",
            arguments = listOf(
                navArgument(Screens.CreateNoteScreen.noteIdArg) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString(Screens.CreateNoteScreen.noteIdArg)
            CreateNoteRoot(
                navigateBack = { outerNavController.popBackStack() }
            )
        }

        composable(Screens.CreateNoteScreen.routeBase) {
            CreateNoteRoot(
                navigateBack = { outerNavController.popBackStack() }
            )
        }
    }
}

private const val NAVIGATE_ANIMATION_DURATION = 300