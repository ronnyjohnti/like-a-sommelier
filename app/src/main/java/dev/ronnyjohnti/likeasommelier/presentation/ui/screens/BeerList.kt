package dev.ronnyjohnti.likeasommelier.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.ronnyjohnti.likeasommelier.data.local.BeerDatabaseHelper
import dev.ronnyjohnti.likeasommelier.data.model.Beer

@Composable
fun BeerList() {
    val context = LocalContext.current
    val databaseHelper = remember { BeerDatabaseHelper(context) }
    val beers = remember { mutableStateListOf<Beer>() }

    LaunchedEffect(Unit) {
        beers.clear()
        beers.addAll(databaseHelper.getBeers())
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(beers) { beer ->
            Card(modifier = Modifier.padding(8.dp)) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Brand: ${beer.brand}")
                    Text("Type: ${beer.type}")
                    Text("Alcohol: ${beer.alcohol}%")
                    Text("Rating: ${beer.rating}")
                }
            }
        }
    }
}
