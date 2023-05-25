package com.example.weatherapp.data

import com.example.weatherapp.model.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //http://api.openweathermap.org
    ///data/2.5/weather?q={cityOrID}&APPID=fca37e260100700636a1e3b9cb24a997

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Response<WeatherData>

    /*
    @GET("data/2.5/weather?q={id}&appid={appid}")
    fun getWeather(
        @Query("id") cityName : Int?,
        @Query("appid") apiKey : String = Constants.API_KEY
    ) : Response<WeatherList>
     */

}