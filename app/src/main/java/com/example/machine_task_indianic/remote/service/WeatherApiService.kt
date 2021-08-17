package com.example.machine_task_indianic.remote.service

import com.example.machine_task_indianic.remote.model.ForecastData
import com.example.machine_task_indianic.remote.model.WeatherData
import com.example.machine_task_indianic.utils.URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherApiService {

    @GET(URL.WEATHER_URL)
    suspend fun getWeather(@QueryMap parameters: Map<String, String>): Response<WeatherData>

    @GET(URL.FORECAST_URL)
    suspend fun getForecast(@QueryMap parameters: Map<String, String>): Response<ForecastData>
}