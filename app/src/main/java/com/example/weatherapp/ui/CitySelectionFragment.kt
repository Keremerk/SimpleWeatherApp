package com.example.weatherapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.adapter.CityAdapter
import com.example.weatherapp.databinding.FragmentCitySelectionBinding
import com.example.weatherapp.model.WeatherData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.io.InputStreamReader

class CitySelectionFragment : Fragment() {
    private lateinit var cityAdapter : CityAdapter
    private lateinit var list : List<WeatherData>

    private lateinit var binding : FragmentCitySelectionBinding
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

        val assetManager = requireContext().assets
        val inputStream = assetManager.open("city.list.json")
        val reader = JsonReader(InputStreamReader(inputStream, "UTF-8"))


        try {
            val gson = Gson()
            val cityListType = object : TypeToken<ArrayList<WeatherData>>() {}.type
            list = gson.fromJson(reader, cityListType)
            println(list[0].name)
            println(list[1].name)
        } catch (e : Exception) {
            println("exception catch")
            // Handle the exception
        } finally {
            reader.close()
        }

        setAdapters()

        binding.citySearchET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s : CharSequence, start : Int, count : Int, after : Int) {}
            override fun onTextChanged(s : CharSequence, start : Int, before : Int, count : Int) {
                cityAdapter.filter2(binding.citySearchET.text.toString())

            }
            override fun afterTextChanged(s : Editable) {
                cityAdapter.filter2(binding.citySearchET.text.toString())
            }
        })

    }


    private fun setAdapters() {
        binding.apply {
            citiesRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

                cityAdapter = CityAdapter(list as ArrayList<WeatherData>) { position ->
                val clickedCityName = list[position].name

                val bundle = Bundle().apply {
                    putString("cityName", clickedCityName)
                }

               // Use childFragmentManager instead of supportFragmentManager
                val transaction = childFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragmentContainerView2, HomeFragment().apply {
                        arguments = bundle
                    })


                    transaction.commit()
                println(position)
                println(clickedCityName)
                println("this is the clicked categories' styles $clickedCityName")


            }
            citiesRecyclerView.adapter = cityAdapter
        }
    }


}