package com.example.examentomadelectura.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadingDao {
    @Insert
    suspend fun insertReading(reading: Reading)

    @Query("SELECT * FROM readings ORDER BY date DESC")
    fun getAllReadings(): Flow<List<Reading>>
}