package com.clove.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.clove.R

@Composable
fun BottomNavBar(
    navController: NavHostController,
    currentRoute: String?
) {
    NavigationBar {
        NavigationBarItem(
            icon = {
                Icon(painter = painterResource(id = R.drawable.ic_home), contentDescription = "Home")
            },
            label = { Text("Home") },
            selected = currentRoute == "home",
            onClick = {
                if (currentRoute != "home") navController.navigate("home")
            }
        )
        NavigationBarItem(
            icon = {
                Icon(painter = painterResource(id = R.drawable.ic_meditation), contentDescription = "Meditate")
            },
            label = { Text("Meditate") },
            selected = currentRoute == "meditate",
            onClick = {
                if (currentRoute != "meditate") navController.navigate("meditate")
            }
        )
        NavigationBarItem(
            icon = {
                Icon(painter = painterResource(id = R.drawable.ic_alarm), contentDescription = "Alarm")
            },
            label = { Text("Alarm") },
            selected = currentRoute == "alarm",
            onClick = {
                if (currentRoute != "alarm") navController.navigate("alarm")
            }
        )
        NavigationBarItem(
            icon = {
                Icon(painter = painterResource(id = R.drawable.ic_settings), contentDescription = "Settings")
            },
            label = { Text("Settings") },
            selected = currentRoute == "settings",
            onClick = {
                if (currentRoute != "settings") navController.navigate("settings")
            }
        )
    }
}