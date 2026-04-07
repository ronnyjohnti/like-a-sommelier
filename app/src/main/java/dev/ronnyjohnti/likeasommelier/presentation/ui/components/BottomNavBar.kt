package dev.ronnyjohnti.likeasommelier.presentation.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController

@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(BottomNavItem.BeerList, BottomNavItem.BeerForm)
    var selectedItem by remember { mutableStateOf(BottomNavItem.BeerList.route) }

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = selectedItem == item.route,
                onClick = {
                    selectedItem = item.route
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}
