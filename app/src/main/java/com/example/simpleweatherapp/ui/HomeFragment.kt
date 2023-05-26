package com.example.simpleweatherapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.simpleweatherapp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val weatherViewModel : WeatherViewModel by viewModels()
    private lateinit var city : String
    private lateinit var randomNumbers : List<Int>

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?
    ) : View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        city = arguments?.getString("cityName") ?: "Istanbul" // Assign default city if null
        val currentCity = binding.cityNameTV.text.toString()
        val bundle = Bundle().apply {
            putString("currentCity", currentCity)
        }
        binding.cities.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_citySelectionFragment2, bundle)
        }

        // Generate random number list
        randomNumbers = generateRandomNumbers(10, 0, 2000)

        chooseCity()
        getWeatherData()
    }

    private fun chooseCity() {
        weatherViewModel.viewModelScope.launch {
            weatherViewModel.getCityWeather(city)
        }
    }

    private fun generateRandomNumbers(count : Int, min : Int, max : Int) : List<Int> {
        val random = Random.Default
        return List(count) { random.nextInt(min, max + 1) }
    }

    @SuppressLint("SetTextI18n")
    private fun getWeatherData() {
        weatherViewModel.viewModelScope.launch {

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
                    val seaLever = randomNumbers.random().toString()

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
                        SeaLevelTV.text = seaLever
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

