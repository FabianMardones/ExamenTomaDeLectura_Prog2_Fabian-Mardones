package com.example.examentomadelectura.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date

@Entity(tableName = "readings")
data class Reading(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: ReadingType,
    val value: Double,
    val date: Instant
)

enum class ReadingType {
    WATER,
    ELECTRICITY,
    GAS
}