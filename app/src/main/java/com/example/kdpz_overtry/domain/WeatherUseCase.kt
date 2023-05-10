package com.example.kdpz_overtry.domain

import com.example.kdpz_overtry.data.repository.WeatherRepository
import com.example.kdpz_overtry.data.retrofit.WeatherClass

object WeatherUseCase {
    suspend fun getWeather(cityName: String): WeatherClass? {
        return WeatherRepository.getWeather(cityName)
    }
}