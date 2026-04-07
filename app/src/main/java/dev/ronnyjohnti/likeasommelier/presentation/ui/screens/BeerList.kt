package dev.ronnyjohnti.likeasommelier.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.ronnyjohnti.likeasommelier.data.local.BeerDatabaseHelper
import dev.ronnyjohnti.likeasommelier.data.model.Beer
import dev.ronnyjohnti.likeasommelier.domain.repository.BeerRepository
import dev.ronnyjohnti.likeasommelier.presentation.ui.components.BeerCard

@Composable
fun BeerList() {
    val context = LocalContext.current
    val databaseHelper = remember { BeerDatabaseHelper(context) }
    val beerRepository = BeerRepository(databaseHelper)
    val beers = remember { mutableStateListOf<Beer>() }

    LaunchedEffect(Unit) {
        beers.clear()
        beers.addAll(beerRepository.getAllBeers())
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(beers) { beer ->
            BeerCard(beer)
        }
    }
}
