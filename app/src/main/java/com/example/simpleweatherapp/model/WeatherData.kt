package com.example.simpleweatherapp.model

data class WeatherData(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: MainData,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)