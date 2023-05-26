package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.CitiesRvItemBinding
import com.example.weatherapp.model.WeatherData

class CityAdapter(
    var citiesList : ArrayList<WeatherData>, val OnclickListener : (Int) -> Unit
) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

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
        return citiesList.size
    }

    fun updateList(newList : List<WeatherData>) {
        citiesList = newList as ArrayList<WeatherData>
        notifyDataSetChanged()
    }

}