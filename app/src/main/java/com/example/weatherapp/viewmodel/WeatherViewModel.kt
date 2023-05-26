package com.example.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository : Repository) : ViewModel() {
    private var weatherData = MutableLiveData<WeatherData>()

    fun getWeatherData() = weatherData

    fun getCityWeather(cityName : String) {
        viewModelScope.launch {
            repository.getCityWeather(cityName).collectLatest { response ->
                    if (response.isSuccessful) {
                        weatherData.value = response.body()
                        // Do something with the weather data
                    } else {
                        println("API request failed")
                    }
                }
        }
    }
}