package com.example.weatherapp.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherList(
    val cities: List<WeatherListItem>
)