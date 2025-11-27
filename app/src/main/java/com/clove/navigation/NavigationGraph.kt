package com.clove.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.clove.ui.screens.AlarmScreen
import com.clove.ui.screens.HomeScreen
import com.clove.ui.screens.Meditation
import com.clove.ui.screens.SettingsScreen
import com.clove.ui.screens.StatsScreen
import com.clove.ui.screens.StressReliefScreen
import com.clove.ui.screens.TimerPickerScreen

// Navigation Host with animated transitions
@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(300)
            )
        }
    ) {
        composable("home") { HomeScreen(navController = navController) }
        composable("stress_relief") { StressReliefScreen() }
        composable("alarm") { AlarmScreen(navController = navController) }
        composable("settings") { SettingsScreen() }
        composable("stats") { StatsScreen(navController = navController) }
        composable(
            "meditation_focus?duration={duration}",
            arguments = listOf(navArgument("duration") {
                type = NavType.IntType
                defaultValue = 0
            })
        ) { backStackEntry ->
            val duration = backStackEntry.arguments?.getInt("duration") ?: 0
            Meditation(navController = navController, timerDuration = duration)
        }
        composable("timer_picker") {
            TimerPickerScreen(
                onTimerSelected = { minutes ->
                    navController.navigate("meditation_focus?duration=$minutes")
                }
            )
        }
    }
}


