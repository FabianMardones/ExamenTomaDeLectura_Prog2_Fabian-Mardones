package com.example.examentomadelectura.data

import com.example.examentomadelectura.data.local.Reading
import com.example.examentomadelectura.data.local.ReadingDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataRepository @Inject constructor(private val readingDao: ReadingDao) {
    val allReadings: Flow<List<Reading>> = readingDao.getAllReadings()

    suspend fun insertReading(reading: Reading) {
        withContext(Dispatchers.IO) {
            readingDao.insertReading(reading)
        }
    }
}
