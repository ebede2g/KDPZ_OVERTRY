package com.example.kdpz_overtry.data.retrofit

import com.example.kdpz_overtry.data.models.*
import com.example.kdpz_overtry.data.models.Weather

data class WeatherClass(
    val weather: List<Weather>,
    val main: Main_,
    val wind: Wind,
    val clouds: Clouds,
    val cod: Int

)