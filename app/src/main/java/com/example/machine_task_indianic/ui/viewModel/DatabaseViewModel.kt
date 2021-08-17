package com.example.machine_task_indianic.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.machine_task_indianic.remote.model.LocationHistoryData
import com.example.machine_task_indianic.remote.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(
    private val repository: DatabaseRepository
) : ViewModel() {

    fun getAllWeatherData(): LiveData<List<LocationHistoryData>> {
        return repository.getAllWeatherData()
    }

    suspend fun addWeatherData(locationHistoryData: LocationHistoryData) {
        return repository.addWeatherData(locationHistoryData)
    }
}