package com.example.examentomadelectura.ui.add_reading

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.tooling.preview.Preview // Puedes mantenerla o quitarla si no generas previews para esta pantalla
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.examentomadelectura.R
import com.example.examentomadelectura.data.local.ReadingType
import java.time.Instant // Importa Instant


fun ReadingType.localized(context: Context): String {
    return when (this) {
        ReadingType.WATER -> context.getString(R.string.water)
        ReadingType.ELECTRICITY -> context.getString(R.string.electricity)
        ReadingType.GAS -> context.getString(R.string.gas)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReadingScreen(
    onNavigateBack: () -> Unit,
    viewModel: AddReadingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val readingTypeValues = remember { ReadingType.values() }

    var selectedType by remember { mutableStateOf(readingTypeValues[0]) }
    var value by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.add_reading_title)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(

                            imageVector = Icons.AutoMirrored.Filled.ArrowBack, //
                            contentDescription = stringResource(R.string.navigate_back)
                        )
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

            Box {
                OutlinedTextField(
                    value = selectedType.localized(context),
                    onValueChange = { },
                    readOnly = true,
                    label = { Text(text = stringResource(R.string.expense_type)) },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = stringResource(R.string.dropdown_description),
                            Modifier.clickable { expanded = true }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    readingTypeValues.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(text = type.localized(context)) },
                            onClick = {
                                selectedType = type
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = value,
                onValueChange = { value = it },
                label = { Text(text = stringResource(R.string.meter_value)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
            )

            Spacer(modifier = Modifier.height(32.dp))


            Button(
                onClick = {
                    val readingValue = value.toDoubleOrNull()
                    if (readingValue != null) {
                        viewModel.insertReading(
                            type = selectedType,
                            value = readingValue,
                            date = Instant.now()
                        )
                        onNavigateBack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.save))
            }
        }
    }


    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}


@Preview(showBackground = true)
@Composable
fun AddReadingScreenPreview() {
    MaterialTheme {
        AddReadingScreen(onNavigateBack = {})
    }
}