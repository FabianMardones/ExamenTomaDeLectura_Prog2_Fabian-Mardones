package com.example.examentomadelectura.ui.reading_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.examentomadelectura.R
import com.example.examentomadelectura.ui.components.ReadingItem
import com.example.examentomadelectura.data.local.Reading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingListScreen(
    onNavigateToAddReading: () -> Unit,
    viewModel: ReadingListViewModel = hiltViewModel()
) {
    val readings by viewModel.allReadings.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.readings_list_title),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )

        },
        floatingActionButton = {
            Button(onClick = onNavigateToAddReading) {
                Text(text = stringResource(R.string.add_reading))
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (readings.isEmpty()) {
                Text(
                    text = stringResource(R.string.no_readings_yet),
                    modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(readings, key = { it.id }) { reading ->
                        ReadingItem(reading = reading)
                    }
                }
            }
        }
    }
}