package com.example.weatherapp.model

data class Sys(
    val type : Int,
    val id : Int,
    val country : String,
    val sunrise : Long,
    val sunset : Long
)