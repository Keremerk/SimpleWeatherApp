package com.example.weatherapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.adapter.CityAdapter
import com.example.weatherapp.databinding.FragmentCitySelectionBinding
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.utils.CityList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStreamReader

class CitySelectionFragment : Fragment() {
    private lateinit var binding : FragmentCitySelectionBinding
    private lateinit var cityAdapter : CityAdapter
    private lateinit var filteredList : MutableList<WeatherData>
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?
    ) : View {
        binding = FragmentCitySelectionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setAdapters()

        filteredList = mutableListOf()



        binding.citySearchET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s : CharSequence, start : Int, count : Int, after : Int) {}
            override fun onTextChanged(s : CharSequence, start : Int, before : Int, count : Int) {
                filteredList =
                    CityList.list.filter { it.name.lowercase().contains(s.toString().lowercase()) }
                        .toMutableList()

                if (filteredList.isEmpty()) {
                    cityAdapter.updateList(CityList.list as ArrayList<WeatherData>)
                } else {
                    cityAdapter.updateList(filteredList as ArrayList<WeatherData>)
                }
            }

            override fun afterTextChanged(s : Editable) {}
        })
    }

    private fun setAdapters() {
        binding.apply {
            citiesRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            cityAdapter = CityAdapter(CityList.list as ArrayList<WeatherData>) { position ->
                val clickedCityName = if (filteredList.isEmpty()) {
                    CityList.list[position].name
                } else {
                    filteredList[position].name
                }

                val bundle = Bundle().apply {
                    putString("cityName", clickedCityName)
                }
                findNavController().navigate(
                    R.id.action_citySelectionFragment_to_homeFragment2,
                    bundle
                )
            }
            citiesRecyclerView.adapter = cityAdapter
        }
    }
}