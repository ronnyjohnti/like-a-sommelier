package dev.ronnyjohnti.likeasommelier.presentation.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import dev.ronnyjohnti.likeasommelier.data.local.BeerDatabaseHelper
import dev.ronnyjohnti.likeasommelier.data.model.Beer
import dev.ronnyjohnti.likeasommelier.domain.repository.BeerRepository
import dev.ronnyjohnti.likeasommelier.presentation.ui.components.StarRating
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun BeerForm() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val focusRequester = remember { FocusRequester() }
    val coroutineScope = rememberCoroutineScope()
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var tempPhotoFile by remember { mutableStateOf<File?>(null) }
    var photoTaken by remember { mutableStateOf(false) }

    val cameraPermissionState = rememberPermissionState(permission = android.Manifest.permission.CAMERA)

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            photoTaken = true
        }
    }

    LaunchedEffect(Unit) {
        if (cameraPermissionState.status.isGranted) {
            val file = File.createTempFile("beer_photo_", ".jpg", context.cacheDir).apply {
                createNewFile()
                deleteOnExit()
            }

            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file,
            )

            tempPhotoFile = file
            photoUri = uri
            cameraLauncher.launch(uri)
        } else {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    var brand by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var alcohol by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0f) }

    Column(modifier = Modifier.padding(16.dp)) {
        if (photoTaken && photoUri != null) {
            Image(
                painter = rememberAsyncImagePainter(photoUri),
                contentDescription = "Foto da cerveja",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }

        OutlinedTextField(
            value = brand,
            onValueChange = { brand = it },
            label = { Text("Brand") },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged {
                    if (it.isFocused) {
                        coroutineScope.launch {
                            scrollState.animateScrollTo(scrollState.value + 100)
                        }
                    }
                }
        )
        OutlinedTextField(value = type, onValueChange = { type = it }, label = { Text("Type") })
        OutlinedTextField(value = alcohol, onValueChange = { alcohol = it }, label = { Text("Alcohol %") })

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Rating", style = MaterialTheme.typography.labelLarge)
        StarRating(
            rating = rating,
            onRatingChanged = { rating = it },
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val beer = Beer(brand, type, alcohol, rating)
            BeerRepository(databaseHelper = BeerDatabaseHelper(context)).addBeer(beer)
        }) {
            Text("Save Beer")
        }
    }
}
