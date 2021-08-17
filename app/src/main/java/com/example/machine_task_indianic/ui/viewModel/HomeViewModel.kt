package com.example.machine_task_indianic.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machine_task_indianic.remote.model.ForecastData
import com.example.machine_task_indianic.remote.model.Parameter
import com.example.machine_task_indianic.remote.model.WeatherData
import com.example.machine_task_indianic.remote.repository.WeatherRepositoryImpl
import com.example.machine_task_indianic.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: WeatherRepositoryImpl
) : ViewModel() {

    private val weatherMutableLivaData = MutableLiveData<Resource<WeatherData>>()
    private val forecastMutableLivaData = MutableLiveData<Resource<ForecastData>>()

    val weatherLivaData: LiveData<Resource<WeatherData>> get() = weatherMutableLivaData
    val forecastLivaData: LiveData<Resource<ForecastData>> get() = forecastMutableLivaData

    var latitude: Double? = null
    var longitude: Double? = null
    var address: String? = null
    var isDataAdded: Boolean = false

    fun getWeather(parameter: Parameter) = viewModelScope.launch {
        weatherMutableLivaData.postValue(Resource.loading(null))
        mainRepository.getWeather(parameter).let {
            if (it.isSuccessful) {
                weatherMutableLivaData.postValue(Resource.success(it.body()))
            } else {
                weatherMutableLivaData.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

    fun getForecast(parameter: Parameter) = viewModelScope.launch {
        forecastMutableLivaData.postValue(Resource.loading(null))
        mainRepository.getForecast(parameter).let {
            if (it.isSuccessful) {
                forecastMutableLivaData.postValue(Resource.success(it.body()))
            } else {
                forecastMutableLivaData.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

}