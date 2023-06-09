package com.example.simpleweatherapp.data

import com.example.simpleweatherapp.model.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") cityName : String,
        @Query("appid") apiKey : String,
        @Query("units") units : String
    ) : Response<WeatherData>
}