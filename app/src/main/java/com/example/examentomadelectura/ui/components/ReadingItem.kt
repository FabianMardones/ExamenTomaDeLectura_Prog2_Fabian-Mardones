package com.example.examentomadelectura.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.examentomadelectura.data.local.Reading
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ReadingItem(reading: Reading) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Tipo: ${reading.type}")
            Text(text = "Valor: ${reading.value}")
            Text(text = "Fecha: ${dateFormat.format(reading.date)}")
        }
    }
}
