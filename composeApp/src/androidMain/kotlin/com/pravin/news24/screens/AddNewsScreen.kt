package com.pravin.news24.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.pravin.news24.R
import com.pravin.news24.Screens
import com.pravin.news24.theme.AppTheme
import com.pravin.news24.R.string as AppText



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewsScreen(
    onNavigateClick:(String) -> Unit
) {
    val title = remember { mutableStateOf("") }
    val category = remember { mutableStateOf("") }
    val content = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var isExpanded by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            Log.d("AddNewsScreen", "Selected image URI: $it")
            context.contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            selectedImageUri = it
        } ?: run {
            Log.d("AddNewsScreen", "No image selected")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    stringResource(AppText.add),
                    color = MaterialTheme.colorScheme.onPrimary) },
                navigationIcon = {
                    IconButton(onClick = { onNavigateClick(Screens.Home.route) }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(AppText.back),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    label = { Text("Title") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = {
                        isExpanded = !isExpanded
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = category.value,
                        onValueChange = { category.value = it },
                        label = { Text("Category") },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = false)
                        }

                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = content.value,
                    onValueChange = { content.value = it },
                    label = { Text("Content") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    label = { Text("Description") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .clickable {
                            pickImageLauncher.launch(arrayOf("image/*"))
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (selectedImageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(model = selectedImageUri),
                            contentDescription = "Selected Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.upload),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onNavigateClick(Screens.Home.route) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Add News", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    )
}

@Preview
@Composable
private fun AddScreenPreview() {
    AppTheme {
        AddNewsScreen(
            onNavigateClick = {}
        )
    }
}