package com.example.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.model.WeatherList
import com.example.weatherapp.model.WeatherListItem
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.InputStreamReader

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val apiViewModel : WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        //getPrompts()
        //getVideoImageResult()
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val assetManager = requireContext().assets
        val inputStream = assetManager.open("city.list.json")
        val reader = JsonReader(InputStreamReader(inputStream, "UTF-8"))

        try {
            val gson = Gson()
            val cityListType = object : TypeToken<List<WeatherListItem>>() {}.type
            val cityList = gson.fromJson<List<WeatherListItem>>(reader, cityListType)
            val weatherList = WeatherList(cityList) // Create WeatherList object with the city list

            apiViewModel.viewModelScope.launch {
                apiViewModel.setCityList(weatherList)
                val city = apiViewModel.getCityByName("Istanbul")
                if (city != null) {
                    val cityName = city.name
                    val lat = city.coord.lat
                    val lon = city.coord.lon
                    val humidity = city.main?.humidity ?: 0
                    val feelsLike = city.main?.feels_like ?: 0
                    val temp = city.main?.temp
                    println(city)

                    binding.apply {
                        cityNameTV.text = cityName
                        //currentDegrees.text =
                        latitudeTV.text = "Latitude: $lat"
                        longtitudeTV.text = "Latitude: $lon"
                        humidityPercentage.text = "%$humidity"
                        feelsLikeTV.text = "Feels Like: $feelsLike C "
                        currentDegrees.text = "Current: $temp C"
                    }
                } else {
                    // City not found
                    println("City not found")
                }
            }
        } catch (e : Exception) {
            // Handle the exception
        } finally {
            reader.close()
        }

        /*
          // Use the cityList here
        apiViewModel.viewModelScope.launch {
            apiViewModel.setCityList(cityList)
        }
         */

        getCityWeather()

    }

    private fun getCityWeather() {
        apiViewModel.viewModelScope.launch {
            try {
                apiViewModel.getCityWeather("London")
            } catch (e : Exception) {
                Toast.makeText(requireContext(), "Error inside Home Fragment", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}