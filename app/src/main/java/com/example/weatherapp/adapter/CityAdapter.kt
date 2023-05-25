package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.CitiesRvItemBinding
import com.example.weatherapp.model.WeatherData
import java.util.*

class CityAdapter(
    private var citiesList : ArrayList<WeatherData>,
    val OnclickListener : (Int) -> Unit
) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {
    private var filteredList : MutableList<WeatherData> = citiesList.toMutableList()


    class ViewHolder(binding : CitiesRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val cityName = binding.cityNameTvItem
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ViewHolder {
        val binding =
            CitiesRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int) {
        val item = citiesList[position]

        holder.cityName.text = item.name

        holder.itemView.setOnClickListener {
            OnclickListener(position)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount() : Int {
        return filteredList.size
    }


    fun filter2(text: String) {
        filteredList = if (text.isEmpty()) {
            citiesList.toMutableList()
        } else {
            citiesList.filter {
                it.name.contains(text, true)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }


    /*
    fun filter(query : String) {
        filteredList = if (query.isEmpty()) {
            citiesList
        } else {
            citiesList.filter { item ->
                item.name.lowercase().contains(query.lowercase())
            }
        }
        notifyDataSetChanged()
    }

     */

}