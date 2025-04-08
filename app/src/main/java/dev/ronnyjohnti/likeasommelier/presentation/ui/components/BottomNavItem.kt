package dev.ronnyjohnti.likeasommelier.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object BeerList : BottomNavItem("beer_list", Icons.AutoMirrored.Filled.List, "Reviews")
    data object BeerForm : BottomNavItem("beer_form", Icons.Filled.Add, "Add Review")
}
