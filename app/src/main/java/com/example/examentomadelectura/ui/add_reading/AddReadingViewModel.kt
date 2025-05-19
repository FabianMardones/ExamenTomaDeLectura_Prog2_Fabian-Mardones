package com.example.examentomadelectura.ui.add_reading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examentomadelectura.data.DataRepository
import com.example.examentomadelectura.data.local.Reading
import com.example.examentomadelectura.data.local.ReadingType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.util.Date

@HiltViewModel
class AddReadingViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    fun insertReading(type: ReadingType, value: Double, date: Date) {
        viewModelScope.launch {
            val reading = Reading(type = type, value = value, date = date)
            dataRepository.insertReading(reading)
        }
    }
}
