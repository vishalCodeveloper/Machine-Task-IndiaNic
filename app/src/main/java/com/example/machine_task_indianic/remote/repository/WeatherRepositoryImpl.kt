package com.example.machine_task_indianic.remote.repository

import com.example.machine_task_indianic.remote.model.Parameter
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRepository: WeatherRepository
){

    suspend fun getWeather(parameter: Parameter) = weatherRepository.getWeather(parameter)

    suspend fun getForecast(parameter: Parameter) = weatherRepository.getForecast(parameter)
}