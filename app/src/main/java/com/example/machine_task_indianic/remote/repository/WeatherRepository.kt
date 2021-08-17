package com.example.machine_task_indianic.remote.repository

import com.example.machine_task_indianic.remote.model.ForecastData
import com.example.machine_task_indianic.remote.model.Parameter
import com.example.machine_task_indianic.remote.model.WeatherData
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeather(parameter: Parameter): Response<WeatherData>
    suspend fun getForecast(parameter: Parameter): Response<ForecastData>
}