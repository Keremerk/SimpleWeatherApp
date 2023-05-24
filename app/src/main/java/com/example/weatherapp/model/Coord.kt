package com.example.weatherapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Coord (
    val lon: Double,
    val lat: Double
)