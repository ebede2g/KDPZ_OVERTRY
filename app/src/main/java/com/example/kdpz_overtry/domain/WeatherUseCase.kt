package com.example.kdpz_overtry.domain

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.kdpz_overtry.data.repository.WeatherRepository
import com.example.kdpz_overtry.data.retrofit.WeatherClass
import kotlinx.coroutines.launch

object WeatherUseCase {
    suspend fun getWeather(cityName: String): WeatherClass? {



        try {
            val weather = WeatherRepository.getWeather(cityName)
            Log.d("data", "data receive: success")

            weather?.main.temp = "${weather!!.main.temp}°C"
        }
        catch (e: Exception) {
            val weather = WeatherClass()
            Log.d("data", "data receive: failure")
        }

        return WeatherRepository.getWeather(cityName)
    }
}