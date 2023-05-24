package com.example.weatherapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class Main(
    val temp: Double,

    @SerialName("feels_like")
    val feels_like: Double,

    @SerialName("temp_min")
    val temp_min: Double,

    @SerialName("temp_max")
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int
)