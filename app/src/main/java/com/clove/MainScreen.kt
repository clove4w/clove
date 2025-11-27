package com.clove

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.clove.navigation.BottomNavBar
import com.clove.navigation.NavigationHost

//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//    val backStackEntry = navController.currentBackStackEntryAsState()
//    val currentRoute = backStackEntry.value?.destination?.route
//
//    Scaffold(
//        contentWindowInsets = androidx.compose.foundation.layout.WindowInsets(0),
//        bottomBar = {
//            BottomNavBar(
//                navController = navController,
//                currentRoute = currentRoute
//            )
//        }
//    ) { innerPadding ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//        ) {
//            NavigationHost(navController)
//        }
//    }
//}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    Scaffold(
        contentWindowInsets = WindowInsets(0),
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavigationHost(navController)

            // Floating Bottom Navigation Bar
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(
                    modifier = Modifier.padding(bottom = 40
                        .dp)
                ) {
                    BottomNavBar(
                        navController = navController,
                        currentRoute = currentRoute
                    )
                }
            }
        }
    }
}



