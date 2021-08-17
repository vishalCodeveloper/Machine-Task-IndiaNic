package com.example.machine_task_indianic.remote.model

import com.google.gson.annotations.SerializedName

data class Parameter(
    @SerializedName("lat")
    var lat: String? = null,

    @SerializedName("lon")
    var lon: String? = null,

    @SerializedName("appid")
    var appid: String? = null,
    @SerializedName("units")
    var units: String? = null
)
