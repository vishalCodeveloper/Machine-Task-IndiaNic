package com.example.machine_task_indianic.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.machine_task_indianic.remote.model.LocationHistoryData

@Dao
interface WeatherDAO {
    @Query("SELECT * FROM location_history")
    fun getAllWeatherData() : LiveData<List<LocationHistoryData>>

    @Insert
    suspend fun addWeatherData(locationHistoryData: LocationHistoryData)
}