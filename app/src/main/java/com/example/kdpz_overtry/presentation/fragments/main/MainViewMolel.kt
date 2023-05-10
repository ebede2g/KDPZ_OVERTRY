package com.example.kdpz_overtry.presentation.fragments.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.kdpz_overtry.data.retrofit.WeatherClass
import com.example.newnews.data.factories.ApiRun
import kotlinx.coroutines.launch
import kotlin.Exception

class MainViewMolel : ViewModel() {
    val weatherLiveData = MutableLiveData<WeatherClass?>()
    fun getWeather(cityName: String) {
        viewModelScope.launch {

            val weather = ApiRun.setApi.getProductById(cityName)

            weatherLiveData.postValue(weather)

            binding.cityName.text = cityName
            binding.currentTemp.text = "${weatherClass.main.temp.toString()}°C"
            binding.weatherDescr.text=weatherClass.weather[0].description
            Glide.with(binding.weatherImage.context).load("https://openweathermap.org/img/wn/${weatherClass.weather[0].icon}@2x.png").into(binding.weatherImage)

        }
    }
}