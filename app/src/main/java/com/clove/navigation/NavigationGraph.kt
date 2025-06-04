package com.clove.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.clove.ui.screens.AlarmScreen
import com.clove.ui.screens.HomeScreen // Import your original HomeScreen
import com.clove.ui.screens.Meditation
import com.clove.ui.screens.SettingsScreen


// Navigation Host
@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen() } // Original HomeScreen
        composable("meditate") { Meditation() }
        composable("alarm") { AlarmScreen() }
        composable("settings") { SettingsScreen() }
    }
}


