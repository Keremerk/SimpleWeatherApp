package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.utils.CityList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dagger.hilt.android.AndroidEntryPoint
import java.io.InputStreamReader

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        try {
            val assetManager = this.assets
            val inputStream = assetManager.open("city.list.json")
            val reader = JsonReader(InputStreamReader(inputStream, "UTF-8"))

            val gson = Gson()
            val cityListType = object : TypeToken<ArrayList<WeatherData>>() {}.type
            CityList.list = gson.fromJson(reader, cityListType)
            reader.close()
        } catch (e : Exception) {
            println("Exception: ${e.message}")
        }
    }
}



