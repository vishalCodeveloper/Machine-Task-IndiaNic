package com.example.machine_task_indianic.remote.datasource

import com.example.machine_task_indianic.BuildConfig
import com.example.machine_task_indianic.remote.service.WeatherApiService
import com.example.machine_task_indianic.remote.model.ForecastData
import com.example.machine_task_indianic.remote.model.Parameter
import com.example.machine_task_indianic.remote.model.WeatherData
import com.example.machine_task_indianic.remote.repository.WeatherRepository
import com.example.machine_task_indianic.utils.URL
import com.example.machine_task_indianic.utils.serializeToMap
import retrofit2.Response
import javax.inject.Inject

class WeatherLiveDataSource @Inject constructor(
    private val weatherApiService: WeatherApiService
) : WeatherRepository {

    override suspend fun getWeather(parameter: Parameter): Response<WeatherData> {
        parameter.units = URL.METRIC
        parameter.appid = BuildConfig.WEATHER_API
        val personAsMap: Map<String, String> = parameter.serializeToMap()
        return weatherApiService.getWeather(personAsMap)
    }

    override suspend fun getForecast(parameter: Parameter): Response<ForecastData> {
        parameter.units = URL.METRIC
        parameter.appid = BuildConfig.WEATHER_API
        val personAsMap: Map<String, String> = parameter.serializeToMap()
        return weatherApiService.getForecast(personAsMap)
    }
}