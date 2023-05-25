package com.example.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.google.gson.stream.JsonReader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.InputStreamReader

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val weatherViewModel : WeatherViewModel by viewModels()
    private lateinit var city:String
    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?
    ) : View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        city = (arguments?.getString("cityName", "London") ?: 0) as String


        chooseCity()
        getWeatherData()
    }

    private fun chooseCity() {
        weatherViewModel.viewModelScope.launch {
            println("deneme.launch")

            weatherViewModel.getCityWeather(city)
        }
    }

    private fun getWeatherData() {
        weatherViewModel.viewModelScope.launch {
            println("viewModelScope.launch")

            weatherViewModel.getWeatherData().observe(viewLifecycleOwner) { city ->
                println(city)

                if (city != null) {
                    val cityName = city.name
                    val temp = city.main.temp.toInt()
                    val feelsLike = city.main.feels_like.toInt()
                    val highestDegree = city.main.temp_max.toInt()
                    val lowestDegree = city.main.temp_min.toInt()
                    val humidity = city.main.humidity
                    val windSpeed = city.wind.speed
                    val windGust = city.wind.deg

                    val lat = city.coord.lat
                    val lon = city.coord.lon



                    binding.apply {
                        //City Name
                        cityNameTV.text = cityName
                        //Current-Feels Like Degree
                        currentDegrees.text = "Current: $temp °C"
                        feelsLikeTV.text = "Feels Like: $feelsLike °C"
                        //Highest-Lowest Degree
                        highestDegreesTV.text = "H: $highestDegree °C"
                        lowestDegreeTV.text = "L: $lowestDegree °C"
                        //Humidity
                        humidityPercentage.text = "%$humidity"
                        //Wind
                        windSpeedTV.text = "Speed: $windSpeed km/h"
                        windGustTV.text = "Gust: $windGust km/h"
                        //Sea Level
                        //Coordinates
                        latitudeTV.text = "Latitude: $lat"
                        longtitudeTV.text = "Longitude: $lon"
                    }
                } else {
                    // City not found
                    println("City not found")
                }
            }
        }
    }
}

