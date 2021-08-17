package com.example.machine_task_indianic.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.machine_task_indianic.remote.model.LocationHistoryData

@Database(
    entities = [LocationHistoryData::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDAO(): WeatherDAO
}