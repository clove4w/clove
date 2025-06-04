//package com.clove.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.clove.ui.screens.WelcomeScreen
//import com.clove.ui.screens.UserNameScreen
//
//@Composable
//fun NavigationSetup() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "welcome") {
//        composable("welcome") {
//            WelcomeScreen(navController)
//        }
//        composable("username") {
//            UserNameScreen()
//        }
//    }
//}
