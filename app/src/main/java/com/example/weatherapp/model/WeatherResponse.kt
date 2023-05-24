package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val weatherList : List<WeatherListItem>
)