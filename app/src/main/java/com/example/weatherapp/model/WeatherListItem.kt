package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
@Serializable
data class WeatherListItem(
    @SerializedName("coord") val coord: Coord,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("base") val base: String,
    @SerializedName("main") val main: Main,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("dt") val dt: Long,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("cod") val cod: Int
)
