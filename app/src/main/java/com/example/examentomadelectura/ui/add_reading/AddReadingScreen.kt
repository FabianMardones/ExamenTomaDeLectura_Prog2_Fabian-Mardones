package com.example.examentomadelectura.ui.add_reading

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.examentomadelectura.R
import com.example.examentomadelectura.data.local.ReadingType
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReadingScreen(
    onNavigateBack: () -> Unit,
    viewModel: AddReadingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val waterString = stringResource(R.string.water)
    val electricityString = stringResource(R.string.electricity)
    val gasString = stringResource(R.string.gas)
    val readingTypes = listOf(
        waterString,
        electricityString,
        gasString
    )
    var selectedType by remember { mutableStateOf(readingTypes[0]) }
    var value by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.add_reading_title)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        // Icono de flecha hacia atrás (puedes usar un icono de Material Icons)
                        // Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Dropdown para el tipo de gasto
            Box {
                Row(
                    modifier = Modifier
                        .clickable { expanded = true }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.expense_type))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = selectedType)
                    Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
                }

                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    readingTypes.forEach { type ->
                        DropdownMenuItem(text = { Text(text = type) }, onClick = {
                            selectedType = type
                            expanded = false
                        })
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo para el valor del medidor
            OutlinedTextField(
                value = value,
                onValueChange = { value = it },
                label = { Text(text = stringResource(R.string.meter_value)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.focusRequester(focusRequester)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón para guardar
            Button(onClick = {
                // Use the captured string values here
                val readingType = when (selectedType) {
                    waterString -> ReadingType.WATER
                    electricityString -> ReadingType.ELECTRICITY
                    gasString -> ReadingType.GAS
                    else -> ReadingType.WATER
                }
                viewModel.insertReading(
                    readingType,
                    value.toDoubleOrNull() ?: 0.0,
                    Date()
                )
                onNavigateBack()
            }) {
                Text(text = stringResource(R.string.save))
            }
        }
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

