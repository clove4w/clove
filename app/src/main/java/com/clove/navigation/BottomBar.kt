package com.clove.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.clove.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

@Composable
fun BottomNavBar(
    navController: NavHostController,
    currentRoute: String?
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .height(75.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(40.dp)),
        tonalElevation = 8.dp,
        shadowElevation = 8.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                icon = R.drawable.ic_home,
                label = "Home",
                isSelected = currentRoute == "home",
                onClick = { if (currentRoute != "home") navController.navigate("home") }
            )
            BottomNavItem(
                icon = R.drawable.ic_meditation,
                label = "Meditate",
                isSelected = currentRoute == "meditate",
                onClick = { if (currentRoute != "meditate") navController.navigate("meditate") }
            )
            BottomNavItem(
                icon = R.drawable.ic_alarm,
                label = "Alarm",
                isSelected = currentRoute == "alarm",
                onClick = { if (currentRoute != "alarm") navController.navigate("alarm") }
            )
            BottomNavItem(
                icon = R.drawable.ic_settings,
                label = "Settings",
                isSelected = currentRoute == "settings",
                onClick = { if (currentRoute != "settings") navController.navigate("settings") }
            )
        }
    }
}

@Composable
fun BottomNavItem(
    icon: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            //.weight(1f)
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}
