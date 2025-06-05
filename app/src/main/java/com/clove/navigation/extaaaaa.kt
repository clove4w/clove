package com.clove.navigation

// Data class to define screens
//data class Screen(val id: Int, val label: String, val route: String)

// Define the screens
//val screens = listOf(
//    Screen(0, "Home", "home"),
//    Screen(1, "Meditate", "meditate"),
//    Screen(2, "Alarm", "alarm"),
//    Screen(3, "Settings", "settings")
//)

//@Composable
//fun BM() {
//    val navController = rememberNavController()
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        // Host the navigation
//        NavigationHost(navController)
//
//        // Display the bottom navigation bar
//        Surface(
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.BottomCenter),
//            shadowElevation = 8.dp
//        ) {
//            AppWithBottomNavigation(
//                modifier = Modifier
//            )
//        }
//    }
//}

// UI of Nav BAR
// Bottom Navigation Bar with Navigation Support
//@Composable
//fun StaticBottomNavigationBarWithSelection(navController: NavHostController) {
//    Surface(
//        modifier = Modifier.fillMaxWidth(),
//        shape = androidx.compose.foundation.shape.RoundedCornerShape(60.dp, 60.dp, 0.dp, 0.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp),
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // Render each bottom navigation item
//            screens.forEach { screen ->
//                BottomNavItem(
//                    icon = when (screen.id) {
//                        0 -> R.drawable.ic_home
//                        1 -> R.drawable.ic_meditation
//                        2 -> R.drawable.ic_alarm
//                        3 -> R.drawable.ic_settings
//                        else -> R.drawable.ic_home // Default icon
//                    },
//                    label = screen.label,
//                    isSelected = navController.currentBackStackEntry?.destination?.route == screen.route,
//                    onClick = { navController.navigate(screen.route) }
//                )
//            }
//        }
//    }
//}

// Bottom Navigation Item
//@Composable
//fun BottomNavItem(
//    icon: Int,
//    label: String,
//    isSelected: Boolean,
//    onClick: () -> Unit
//) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .padding(8.dp)
//            .clickable { onClick() }
//    ) {
//        Box(
//            modifier = Modifier
//                .padding(8.dp)
//                .background(
//                    color = if (isSelected) Color(0xFF6200EE) else Color.Transparent,
//                    shape = androidx.compose.foundation.shape.CircleShape
//                )
//                .padding(8.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Icon(
//                painter = painterResource(id = icon),
//                contentDescription = label,
//                tint = if (isSelected) Color.White else Color.Gray
//            )
//        }
//        Text(
//            text = label,
//            fontSize = 12.sp,
//            color = if (isSelected) Color(0xFF6200EE) else Color.Gray
//        )
//    }
//}

// Basic screen content composable
//@Composable
//fun ScreenContent(title: String, backgroundColor: Color) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(backgroundColor),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(text = title, fontSize = 24.sp, color = Color.Black)
//    }
//}

// Preview for the navigation system
//@Preview
//@Composable
//fun PreviewAppWithBottomNavigation() {
//    AppWithBottomNavigation()
//}
//@Composable
//fun AppWithBottomNavigation(modifier: Modifier = Modifier) {
//    NavigationBar(
//        modifier = modifier
//    ) {
//        NavigationBarItem(
//            icon = {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_home),
//                    contentDescription = null
//                )
//            },
//            label = {
//                Text(text = "Home")
//            },
//            selected = true,
//            onClick = {}
//        )
//        NavigationBarItem(
//            icon = {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_meditation),
//                    contentDescription = null
//                )
//            },
//            label = {
//                Text(text = "Meditate")
//            },
//            selected = false,
//            onClick = {}
//        )
//        NavigationBarItem(
//            icon = {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_alarm),
//                    contentDescription = null
//                )
//            },
//            label = {
//                Text(text = "Alarm")
//            },
//            selected = false,
//            onClick = {}
//        )
//        NavigationBarItem(
//            icon = {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_settings),
//                    contentDescription = null
//                )
//            },
//            label = {
//                Text(text = "Settings")
//            },
//            selected = false,
//            onClick = {}
//        )
//    }
//}

//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//    val backStackEntry = navController.currentBackStackEntryAsState()
//    val currentRoute = backStackEntry.value?.destination?.route
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        NavigationHost(navController)
//
//        Surface(
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.BottomCenter),
//            shadowElevation = 8.dp
//        ) {
//            BottomNavBar(
//                navController = navController,
//                currentRoute = currentRoute
//            )
//        }
//    }
//}