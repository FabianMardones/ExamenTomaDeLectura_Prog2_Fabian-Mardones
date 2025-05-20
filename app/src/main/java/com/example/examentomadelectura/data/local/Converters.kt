package com.example.examentomadelectura.data.local

// Converters.kt
import androidx.room.TypeConverter
import java.time.Instant // Importa Instant

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Instant? {
        return value?.let { Instant.ofEpochMilli(it) }
    }

    @TypeConverter
    fun dateToTimestamp(instant: Instant?): Long? {
        return instant?.toEpochMilli()
    }

    // Los conversores de ReadingType se mantienen iguales
    @TypeConverter
    fun fromReadingType(value: String): ReadingType {
        return ReadingType.valueOf(value)
    }

    @TypeConverter
    fun readingTypeToString(type: ReadingType): String {
        return type.name
    }
}