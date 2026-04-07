package dev.ronnyjohnti.likeasommelier

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.navigation.compose.*
import dev.ronnyjohnti.likeasommelier.presentation.ui.components.BottomNavBar
import dev.ronnyjohnti.likeasommelier.presentation.ui.screens.BeerForm
import dev.ronnyjohnti.likeasommelier.presentation.ui.screens.BeerList

@Composable
fun BeerReviewApp(context: Context) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = "beer_list",
            ) {
                composable("beer_list") {
                    BeerList()
                }
                composable("beer_form") {
                    BeerForm()
                }
            }
        }
    }
}
