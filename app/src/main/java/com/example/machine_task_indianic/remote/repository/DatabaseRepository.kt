package com.example.machine_task_indianic.remote.repository

import com.example.machine_task_indianic.database.WeatherDAO
import com.example.machine_task_indianic.remote.model.LocationHistoryData
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val weatherDAO: WeatherDAO
) {
    fun getAllWeatherData() = weatherDAO.getAllWeatherData()

    suspend fun addWeatherData(locationHistoryData: LocationHistoryData) =
        weatherDAO.addWeatherData(locationHistoryData)
}