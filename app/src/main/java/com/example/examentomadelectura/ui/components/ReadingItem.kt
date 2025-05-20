package com.example.examentomadelectura.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.examentomadelectura.R // Asegúrate de que esta R sea la correcta
import com.example.examentomadelectura.data.local.Reading
import com.example.examentomadelectura.ui.add_reading.localized
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ReadingItem(reading: Reading) {

    val dateTimeFormatter = remember {
        DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault())
            .withZone(ZoneId.systemDefault())
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${stringResource(R.string.reading_item_type_prefix)} ${reading.type.localized(LocalContext.current)}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${stringResource(R.string.reading_item_value_prefix)} ${reading.value}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${stringResource(R.string.reading_item_date_prefix)} ${dateTimeFormatter.format(reading.date)}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}