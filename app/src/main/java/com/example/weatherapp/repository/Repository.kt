package com.example.weatherapp.repository

import com.example.weatherapp.data.ApiService
import com.example.weatherapp.model.WeatherList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiService : ApiService) {
    suspend fun getCityWeather(cityName : String) : Flow<Response<WeatherList>> {
        return flow {
            val response = apiService.getWeather(cityName)
            emit(Response.success(response.body()))
        }.catch {
            val errorResponse = Response.error<WeatherList>(
                500,
                it.message!!.toResponseBody("text/plain".toMediaTypeOrNull())
            )
            emit(errorResponse)
        }
    }
}