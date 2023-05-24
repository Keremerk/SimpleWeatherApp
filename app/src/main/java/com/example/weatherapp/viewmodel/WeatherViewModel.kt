package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherList
import com.example.weatherapp.model.WeatherListItem
import com.example.weatherapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private lateinit var cityList: WeatherList

    fun setCityList(cityList : WeatherList) {
        this.cityList = cityList
    }
    fun getCityByName(cityName: String): WeatherListItem? {
        return cityList.cities.find { it.name == cityName }
    }

//prompts.emit(ResponseModel.Success(response))
    suspend fun getCityWeather(cityName: String) {
        viewModelScope.launch {
            repository.getCityWeather(cityName)
                .collectLatest { response ->
                    if (response.isSuccessful) {
                            println("viewmodel starttraining başarılı")
                            getCityByName(cityName)

                            //cityList.cities[0]
                            //response.body()
                        } else {
                            println("viewmodel starttraining başarısız")

                        }
                }
        }
    }
}