package com.example.machine_task_indianic.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_history")
data class LocationHistoryData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var lat: String,
    var lng: String,
    var address: String,
)