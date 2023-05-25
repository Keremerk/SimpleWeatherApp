package com.example.weatherapp.repository

import com.example.weatherapp.data.ApiService
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiService : ApiService) {
    suspend fun getCityWeather(cityName : String) : Flow<Response<WeatherData>> {
        return flow {
            val response = apiService.getWeather(
                cityName, Constants.API_KEY,
                "metric")
            emit(response)
        }.catch {
            println("Error")
        }
    }
}
