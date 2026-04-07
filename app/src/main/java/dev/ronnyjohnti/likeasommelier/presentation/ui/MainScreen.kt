package dev.ronnyjohnti.likeasommelier.presentation.ui

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.ronnyjohnti.likeasommelier.data.local.BeerDatabaseHelper
import androidx.core.net.toUri
import dev.ronnyjohnti.likeasommelier.data.model.Beer

@Composable
fun MainScreen() {
    var brand by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var alcohol by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    val context = LocalContext.current
    val databaseHelper = remember { BeerDatabaseHelper(context) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = brand, onValueChange = { brand = it }, label = { Text("Brand") })
        OutlinedTextField(value = type, onValueChange = { type = it }, label = { Text("Type") })
        OutlinedTextField(value = alcohol, onValueChange = { alcohol = it }, label = { Text("Alcohol %") })
        OutlinedTextField(value = rating, onValueChange = { rating = it }, label = { Text("Rating") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            databaseHelper.saveBeer(Beer(brand, type, alcohol, rating.toFloat()))
        }) {
            Text("Save Beer")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

fun createImageUri(): Uri {
    return "content://dev.ronnyjohnti.likeasommelier/photo.jpg".toUri()
}
