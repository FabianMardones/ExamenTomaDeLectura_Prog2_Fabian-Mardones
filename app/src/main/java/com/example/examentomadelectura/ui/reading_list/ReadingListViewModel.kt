package com.example.examentomadelectura.ui.reading_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examentomadelectura.data.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ReadingListViewModel @Inject constructor(dataRepository: DataRepository) : ViewModel() {
    val allReadings = dataRepository.allReadings.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}

