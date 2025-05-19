package com.example.examentomadelectura.data.local

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromReadingType(value: String): ReadingType {
        return ReadingType.valueOf(value)
    }

    @TypeConverter
    fun readingTypeToString(type: ReadingType): String {
        return type.name
    }
}