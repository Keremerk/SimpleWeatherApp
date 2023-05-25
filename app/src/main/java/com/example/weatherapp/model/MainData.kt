package com.example.weatherapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class MainData(
    val temp : Double,

    val feels_like : Double,


    val temp_min : Double,


    val temp_max : Double,
    val pressure : Int,
    val humidity : Int
)
