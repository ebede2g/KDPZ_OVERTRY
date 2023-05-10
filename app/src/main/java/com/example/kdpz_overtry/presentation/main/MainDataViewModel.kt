package com.example.kdpz_overtry.presentation.main

import androidx.lifecycle.ViewModel
import com.example.kdpz_overtry.data.retrofit.WeatherClass

class MainDataViewModel:ViewModel() {
    var weather: WeatherClass? = null   //для збереження даних при повороті екрану
}