package com.clove

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clove.ui.screens.GetStartedScreen
import com.clove.ui.screens.WelcomeScreen
import com.clove.utils.AppPreferences

@Composable
fun OnboardingFlow(appPreferences: AppPreferences) {
    val navController = rememberNavController()
    val userName = remember { mutableStateOf("") }

    NavHost(
        navController = navController,
        startDestination = "welcome",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(400)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(400)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(400)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(400)
            )
        }
    ) {
        composable("welcome") {
            WelcomeScreen(
                onContinueClick = {
                    navController.navigate("get_started") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }

        composable("get_started") {
            GetStartedScreen(
                onCompleteClick = { name, photoUri ->
                    userName.value = name
                    appPreferences.saveUserName(name)
                    if (photoUri != null) {
                        appPreferences.saveProfilePhotoUri(photoUri)
                    }
                    appPreferences.markOnboardingComplete()
                    navController.navigate("complete") {
                        popUpTo("get_started") { inclusive = true }
                    }
                }
            )
        }

        composable("complete") {
            // Transition to main app - this signals MainActivity to show MainScreen
            MainScreen()
        }
    }
}
